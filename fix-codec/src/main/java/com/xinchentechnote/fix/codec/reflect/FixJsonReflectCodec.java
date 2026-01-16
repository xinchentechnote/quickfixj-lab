package com.xinchentechnote.fix.codec.reflect;

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

public class FixJsonReflectCodec implements FixJsonCodec<Message> {

  private FixSchema fixSchema;
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public FixJsonReflectCodec(String fixFilePath) throws Exception {
    this.fixSchema = FixXmlDomParser.load(fixFilePath);
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

  private void encodeEntry(ObjectNode note, FieldMap message, FieldEntry entry)
      throws FieldNotFound {
    if (entry.isRequired() || message.isSetField(entry.getDef().getNumber())) {
      switch (entry.getDef().getType()) {
        case BOOLEAN:
          note.put(entry.getName(), message.getBoolean(entry.getDef().getNumber()) ? "Y" : "N");
          break;
        case INT:
        case SEQNUM:
        case LENGTH:
          note.put(entry.getName(), message.getInt(entry.getDef().getNumber()));
          break;
        case FLOAT:
          note.put(entry.getName(), message.getDouble(entry.getDef().getNumber()));
          break;
        default:
          note.put(entry.getName(), message.getString(entry.getDef().getNumber()));
      }
    }
  }

  private void encodeEntry(ObjectNode note, FieldMap message, ComponentEntry entry) {}

  private void encodeEntry(ObjectNode note, FieldMap message, GroupEntry entry) {}

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

  private void decodeEntry(JsonNode node, FieldMap message, FieldEntry entry) {
    if (entry.isRequired() || node.has(entry.getName())) {
      switch (entry.getDef().getType()) {
        case INT:
          message.setInt(entry.getDef().getNumber(), node.get(entry.getName()).asInt());
          break;
        case FLOAT:
          message.setDouble(entry.getDef().getNumber(), node.get(entry.getName()).asDouble());
          break;
        default:
          message.setString(entry.getDef().getNumber(), node.get(entry.getName()).asText());
      }
    }
  }

  private void decodeEntry(JsonNode node, FieldMap message, ComponentEntry entry) {}

  private void decodeEntry(JsonNode node, FieldMap message, GroupEntry entry) {}
}
