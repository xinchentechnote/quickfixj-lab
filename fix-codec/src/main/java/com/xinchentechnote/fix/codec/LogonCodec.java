package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.Logon;

public class LogonCodec implements FixJsonCodec<Logon> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Logon message) throws Exception {
    ObjectNode root = MAPPER.createObjectNode();

    putHeader(root, message);

    putBody(root, message);

    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
  }

  @Override
  public Logon decode(String jsonString) throws Exception {
    JsonNode root = MAPPER.readTree(jsonString);
    Logon logon = new Logon();

    readHeader(root, logon);

    readBody(root, logon);

    return logon;
  }

  private void putHeader(ObjectNode root, Logon msg) throws Exception {
    root.put("BeginString", msg.getHeader().getString(BeginString.FIELD));
    root.put("MsgType", msg.getHeader().getString(MsgType.FIELD));
    root.put("SenderCompID", msg.getHeader().getString(SenderCompID.FIELD));
    root.put("TargetCompID", msg.getHeader().getString(TargetCompID.FIELD));

    if (msg.getHeader().isSetField(MsgSeqNum.FIELD)) {
      root.put("MsgSeqNum", msg.getHeader().getInt(MsgSeqNum.FIELD));
    }
  }

  private void readHeader(JsonNode root, Logon msg) {
    msg.getHeader().setField(new BeginString(root.get("BeginString").asText()));
    msg.getHeader().setField(new MsgType(root.get("MsgType").asText()));
    msg.getHeader().setField(new SenderCompID(root.get("SenderCompID").asText()));
    msg.getHeader().setField(new TargetCompID(root.get("TargetCompID").asText()));

    if (root.has("MsgSeqNum")) {
      msg.getHeader().setField(new MsgSeqNum(root.get("MsgSeqNum").asInt()));
    }
  }

  private void putBody(ObjectNode root, Logon msg) throws Exception {
    if (msg.isSetField(EncryptMethod.FIELD)) {
      root.put("EncryptMethod", msg.getInt(EncryptMethod.FIELD));
    }
    if (msg.isSetField(HeartBtInt.FIELD)) {
      root.put("HeartBtInt", msg.getInt(HeartBtInt.FIELD));
    }
    if (msg.isSetField(ResetSeqNumFlag.FIELD)) {
      root.put("ResetSeqNumFlag", msg.getBoolean(ResetSeqNumFlag.FIELD));
    }
  }

  private void readBody(JsonNode root, Logon msg) {
    if (root.has("EncryptMethod")) {
      msg.setField(new EncryptMethod(root.get("EncryptMethod").asInt()));
    }
    if (root.has("HeartBtInt")) {
      msg.setField(new HeartBtInt(root.get("HeartBtInt").asInt()));
    }
    if (root.has("ResetSeqNumFlag")) {
      msg.setField(new ResetSeqNumFlag(root.get("ResetSeqNumFlag").asBoolean()));
    }
  }
}
