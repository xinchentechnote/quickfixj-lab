package com.xinchentechnote.fix.codec.runtime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xinchentechnote.fix.codec.FixJsonCodec;
import com.xinchentechnote.fix.parser.v2.*;
import java.util.List;
import quickfix.FieldMap;
import quickfix.FieldNotFound;
import quickfix.StringField;
import quickfix.field.MsgType;
import quickfix.fix44.Message;

public class FixJsonRuntimeCodec implements FixJsonCodec<Message> {

  private FixSchema fixSchema;
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public FixJsonRuntimeCodec(FixSchema fixSchema) {
    this.fixSchema = fixSchema;
  }

  @Override
  public String encode(Message message) throws Exception {
    quickfix.Message.Header header = message.getHeader();
    quickfix.Message.Trailer trailer = message.getTrailer();
    StringField field = header.getField(new MsgType());
    MessageDef messageDef = fixSchema.getMessage(field.getValue());
    ObjectNode root = MAPPER.createObjectNode();
    MessageDef headerDef = fixSchema.getHeader();
    MessageDef trailerDef = fixSchema.getTrailer();
    encodeMessage(root, header, headerDef);
    encodeMessage(root, message, messageDef);
    encodeMessage(root, trailer, trailerDef);

    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
  }

  private void encodeMessage(ObjectNode root, FieldMap message, MessageDef messageDef)
      throws FieldNotFound {
    List<Entry> entries = messageDef.getEntries();
    for (Entry entry : entries) {
      if (entry instanceof FieldEntry) {
        encodeEntry(root, message, (FieldEntry) entry);
      } else if (entry instanceof ComponentEntry) {
        encodeEntry(root, message, (ComponentEntry) entry);

      } else if (entry instanceof GroupEntry) {
        encodeEntry(root, message, (GroupEntry) entry);
      }
    }
  }

  private void encodeEntry(ObjectNode note, FieldMap message, FieldEntry fieldEntry)
      throws FieldNotFound {
    if (fieldEntry.isRequired() || message.isSetField(fieldEntry.getDef().getNumber())) {
      switch (fieldEntry.getDef().getType()) {
        case BOOLEAN:
          note.put(
              fieldEntry.getName(),
              message.getBoolean(fieldEntry.getDef().getNumber()) ? "Y" : "N");
          break;
        case INT:
        case SEQNUM:
        case LENGTH:
          note.put(fieldEntry.getName(), message.getInt(fieldEntry.getDef().getNumber()));
          break;
        case FLOAT:
          note.put(fieldEntry.getName(), message.getDouble(fieldEntry.getDef().getNumber()));
          break;
        default:
          note.put(fieldEntry.getName(), message.getString(fieldEntry.getDef().getNumber()));
      }
    }
  }

  private void encodeEntry(ObjectNode note, FieldMap message, ComponentEntry componentEntry) {
    componentEntry
        .getDef()
        .getEntries()
        .forEach(
            entry -> {
              try {
                if (entry instanceof FieldEntry) {
                  encodeEntry(note, message, (FieldEntry) entry);
                } else if (entry instanceof ComponentEntry) {
                  encodeEntry(note, message, (ComponentEntry) entry);
                } else if (entry instanceof GroupEntry) {
                  encodeEntry(note, message, (GroupEntry) entry);
                }
              } catch (FieldNotFound e) {
                throw new RuntimeException(e);
              }
            });
  }

  private void encodeEntry(ObjectNode note, FieldMap message, GroupEntry groupEntry)
      throws FieldNotFound {

    int numTag = groupEntry.getDef().getNumber();

    if (!message.isSetField(numTag)) {
      return;
    }

    int groupCount = message.getInt(numTag);
    if (groupCount <= 0) {
      return;
    }

    var arrayNode = MAPPER.createArrayNode();

    for (int i = 1; i <= groupCount; i++) {
      quickfix.Group group =
          new quickfix.Group(
              groupEntry.getDef().getNumber(), groupEntry.getDef().getDelimiterNumber());

      message.getGroup(i, group);

      ObjectNode groupNode = MAPPER.createObjectNode();

      for (Entry entry : groupEntry.getDef().getEntries()) {
        if (entry instanceof FieldEntry) {
          encodeEntry(groupNode, group, (FieldEntry) entry);
        } else if (entry instanceof ComponentEntry) {
          encodeEntry(groupNode, group, (ComponentEntry) entry);
        } else if (entry instanceof GroupEntry) {
          encodeEntry(groupNode, group, (GroupEntry) entry);
        }
      }

      arrayNode.add(groupNode);
    }

    note.set(groupEntry.getName(), arrayNode);
  }

  @Override
  public Message decode(String jsonString) throws Exception {
    JsonNode root = MAPPER.readTree(jsonString);
    String msgType = root.get("MsgType").asText();
    MessageDef messageDef = fixSchema.getMessage(msgType);
    Message message = new Message();
    MessageDef header = fixSchema.getHeader();
    MessageDef trailer = fixSchema.getTrailer();
    decodeMessage(root, message.getHeader(), header);
    decodeMessage(root, message, messageDef);
    decodeMessage(root, message.getTrailer(), trailer);
    return message;
  }

  private void decodeMessage(JsonNode root, FieldMap message, MessageDef messageDef) {
    List<Entry> entries = messageDef.getEntries();
    for (Entry entry : entries) {
      if (entry instanceof FieldEntry) {
        decodeEntry(root, message, (FieldEntry) entry);
      } else if (entry instanceof ComponentEntry) {
        decodeEntry(root, message, (ComponentEntry) entry);
      } else if (entry instanceof GroupEntry) {
        decodeEntry(root, message, (GroupEntry) entry);
      }
    }
  }

  private void decodeEntry(JsonNode node, FieldMap message, FieldEntry fieldEntry) {
    if (fieldEntry.isRequired() || node.has(fieldEntry.getName())) {
      switch (fieldEntry.getDef().getType()) {
        case INT:
          message.setInt(fieldEntry.getDef().getNumber(), node.get(fieldEntry.getName()).asInt());
          break;
        case FLOAT:
          message.setDouble(
              fieldEntry.getDef().getNumber(), node.get(fieldEntry.getName()).asDouble());
          break;
        default:
          message.setString(
              fieldEntry.getDef().getNumber(), node.get(fieldEntry.getName()).asText());
      }
    }
  }

  private void decodeEntry(JsonNode node, FieldMap message, ComponentEntry componentEntry) {
    componentEntry
        .getDef()
        .getEntries()
        .forEach(
            entry -> {
              if (entry instanceof FieldEntry) {
                decodeEntry(node, message, (FieldEntry) entry);
              } else if (entry instanceof ComponentEntry) {
                decodeEntry(node, message, (ComponentEntry) entry);
              } else if (entry instanceof GroupEntry) {
                decodeEntry(node, message, (GroupEntry) entry);
              }
            });
  }

  private void decodeEntry(JsonNode node, FieldMap message, GroupEntry groupEntry) {

    JsonNode groupArray = node.get(groupEntry.getName());
    if (groupArray == null || !groupArray.isArray()) {
      return;
    }

    int count = groupArray.size();
    message.setInt(groupEntry.getDef().getNumber(), count);

    for (int i = 0; i < count; i++) {
      JsonNode groupNode = groupArray.get(i);

      quickfix.Group group =
          new quickfix.Group(
              groupEntry.getDef().getNumber(), groupEntry.getDef().getDelimiterNumber());

      for (Entry entry : groupEntry.getDef().getEntries()) {
        if (entry instanceof FieldEntry) {
          decodeEntry(groupNode, group, (FieldEntry) entry);
        } else if (entry instanceof ComponentEntry) {
          decodeEntry(groupNode, group, (ComponentEntry) entry);
        } else if (entry instanceof GroupEntry) {
          decodeEntry(groupNode, group, (GroupEntry) entry);
        }
      }

      message.addGroup(group);
    }
  }
}
