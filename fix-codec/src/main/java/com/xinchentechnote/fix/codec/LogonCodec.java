package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.*;

public class LogonCodec implements FixJsonCodec<Logon> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Logon logon) throws Exception {
    ObjectNode root = MAPPER.createObjectNode();
    root.put("BeginString", logon.getHeader().getString(BeginString.FIELD));
    root.put("BodyLength", logon.getHeader().getInt(BodyLength.FIELD));
    root.put("MsgType", logon.getHeader().getString(MsgType.FIELD));
    root.put("SenderCompID", logon.getHeader().getString(SenderCompID.FIELD));
    root.put("TargetCompID", logon.getHeader().getString(TargetCompID.FIELD));
    if (logon.getHeader().isSetField(OnBehalfOfCompID.FIELD)) {
      root.put("OnBehalfOfCompID", logon.getHeader().getString(OnBehalfOfCompID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToCompID.FIELD)) {
      root.put("DeliverToCompID", logon.getHeader().getString(DeliverToCompID.FIELD));
    }
    if (logon.getHeader().isSetField(SecureDataLen.FIELD)) {
      root.put("SecureDataLen", logon.getHeader().getInt(SecureDataLen.FIELD));
    }
    if (logon.getHeader().isSetField(SecureData.FIELD)) {
      root.put("SecureData", logon.getHeader().getString(SecureData.FIELD));
    }
    root.put("MsgSeqNum", logon.getHeader().getInt(MsgSeqNum.FIELD));
    if (logon.getHeader().isSetField(SenderSubID.FIELD)) {
      root.put("SenderSubID", logon.getHeader().getString(SenderSubID.FIELD));
    }
    if (logon.getHeader().isSetField(SenderLocationID.FIELD)) {
      root.put("SenderLocationID", logon.getHeader().getString(SenderLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(TargetSubID.FIELD)) {
      root.put("TargetSubID", logon.getHeader().getString(TargetSubID.FIELD));
    }
    if (logon.getHeader().isSetField(TargetLocationID.FIELD)) {
      root.put("TargetLocationID", logon.getHeader().getString(TargetLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(OnBehalfOfSubID.FIELD)) {
      root.put("OnBehalfOfSubID", logon.getHeader().getString(OnBehalfOfSubID.FIELD));
    }
    if (logon.getHeader().isSetField(OnBehalfOfLocationID.FIELD)) {
      root.put("OnBehalfOfLocationID", logon.getHeader().getString(OnBehalfOfLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToSubID.FIELD)) {
      root.put("DeliverToSubID", logon.getHeader().getString(DeliverToSubID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToLocationID.FIELD)) {
      root.put("DeliverToLocationID", logon.getHeader().getString(DeliverToLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(PossDupFlag.FIELD)) {
      root.put("PossDupFlag", logon.getHeader().getBoolean(PossDupFlag.FIELD));
    }
    if (logon.getHeader().isSetField(PossResend.FIELD)) {
      root.put("PossResend", logon.getHeader().getBoolean(PossResend.FIELD));
    }
    root.put("SendingTime", logon.getHeader().getUtcTimeStamp(SendingTime.FIELD).toString());
    if (logon.getHeader().isSetField(OrigSendingTime.FIELD)) {
      root.put(
          "OrigSendingTime", logon.getHeader().getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (logon.getHeader().isSetField(XmlDataLen.FIELD)) {
      root.put("XmlDataLen", logon.getHeader().getInt(XmlDataLen.FIELD));
    }
    if (logon.getHeader().isSetField(XmlData.FIELD)) {
      root.put("XmlData", logon.getHeader().getString(XmlData.FIELD));
    }
    if (logon.getHeader().isSetField(MessageEncoding.FIELD)) {
      root.put("MessageEncoding", logon.getHeader().getString(MessageEncoding.FIELD));
    }
    if (logon.getHeader().isSetField(LastMsgSeqNumProcessed.FIELD)) {
      root.put("LastMsgSeqNumProcessed", logon.getHeader().getInt(LastMsgSeqNumProcessed.FIELD));
    }
    root.put("EncryptMethod", logon.getInt(EncryptMethod.FIELD));
    root.put("HeartBtInt", logon.getInt(HeartBtInt.FIELD));
    if (logon.isSetField(RawDataLength.FIELD)) {
      root.put("RawDataLength", logon.getInt(RawDataLength.FIELD));
    }
    if (logon.isSetField(RawData.FIELD)) {
      root.put("RawData", logon.getString(RawData.FIELD));
    }
    if (logon.isSetField(ResetSeqNumFlag.FIELD)) {
      root.put("ResetSeqNumFlag", logon.getBoolean(ResetSeqNumFlag.FIELD));
    }
    if (logon.isSetField(NextExpectedMsgSeqNum.FIELD)) {
      root.put("NextExpectedMsgSeqNum", logon.getInt(NextExpectedMsgSeqNum.FIELD));
    }
    if (logon.isSetField(MaxMessageSize.FIELD)) {
      root.put("MaxMessageSize", logon.getInt(MaxMessageSize.FIELD));
    }
    if (logon.isSetField(TestMessageIndicator.FIELD)) {
      root.put("TestMessageIndicator", logon.getBoolean(TestMessageIndicator.FIELD));
    }
    if (logon.isSetField(Username.FIELD)) {
      root.put("Username", logon.getString(Username.FIELD));
    }
    if (logon.isSetField(Password.FIELD)) {
      root.put("Password", logon.getString(Password.FIELD));
    }
    if (logon.isSetField(NoMsgTypes.FIELD)) {
      Logon.NoMsgTypes group = new Logon.NoMsgTypes();
      ArrayNode noMsgTypesNode = MAPPER.createArrayNode();
      for (int i = 1; i <= logon.getGroupCount(NoMsgTypes.FIELD); i++) {
        logon.getGroup(i, group);
        ObjectNode groupNode = MAPPER.createObjectNode();
        groupNode.put("RefMsgType", group.getString(MsgType.FIELD));
        groupNode.put("MsgDirection", String.valueOf(group.getChar(MsgDirection.FIELD)));
        noMsgTypesNode.add(groupNode);
      }
      root.set("NoMsgTypes", noMsgTypesNode);
    }
    if (logon.getTrailer().isSetField(SignatureLength.FIELD)) {
      root.put("SignatureLength", logon.getTrailer().getInt(SignatureLength.FIELD));
    }
    if (logon.getTrailer().isSetField(Signature.FIELD)) {
      root.put("Signature", logon.getTrailer().getString(Signature.FIELD));
    }
    root.put("CheckSum", logon.getTrailer().getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
  }

  @Override
  public Logon decode(String jsonString) throws Exception {
    JsonNode root = MAPPER.readTree(jsonString);
    Logon logon = new Logon();
    logon.getHeader().setField(new BeginString(root.get("BeginString").asText()));
    logon.getHeader().setField(new BodyLength(root.get("BodyLength").asInt()));
    logon.getHeader().setField(new MsgType(root.get("MsgType").asText()));
    logon.getHeader().setField(new SenderCompID(root.get("SenderCompID").asText()));
    logon.getHeader().setField(new TargetCompID(root.get("TargetCompID").asText()));
    if (root.has("OnBehalfOfCompID")) {
      logon.getHeader().setField(new OnBehalfOfCompID(root.get("OnBehalfOfCompID").asText()));
    }
    if (root.has("DeliverToCompID")) {
      logon.getHeader().setField(new DeliverToCompID(root.get("DeliverToCompID").asText()));
    }
    if (root.has("SecureDataLen")) {
      logon.getHeader().setField(new SecureDataLen(root.get("SecureDataLen").asInt()));
    }
    if (root.has("SecureData")) {
      logon.getHeader().setField(new SecureData(root.get("SecureData").asText()));
    }
    logon.getHeader().setField(new MsgSeqNum(root.get("MsgSeqNum").asInt()));
    if (root.has("SenderSubID")) {
      logon.getHeader().setField(new SenderSubID(root.get("SenderSubID").asText()));
    }
    if (root.has("SenderLocationID")) {
      logon.getHeader().setField(new SenderLocationID(root.get("SenderLocationID").asText()));
    }
    if (root.has("TargetSubID")) {
      logon.getHeader().setField(new TargetSubID(root.get("TargetSubID").asText()));
    }
    if (root.has("TargetLocationID")) {
      logon.getHeader().setField(new TargetLocationID(root.get("TargetLocationID").asText()));
    }
    if (root.has("OnBehalfOfSubID")) {
      logon.getHeader().setField(new OnBehalfOfSubID(root.get("OnBehalfOfSubID").asText()));
    }
    if (root.has("OnBehalfOfLocationID")) {
      logon
          .getHeader()
          .setField(new OnBehalfOfLocationID(root.get("OnBehalfOfLocationID").asText()));
    }
    if (root.has("DeliverToSubID")) {
      logon.getHeader().setField(new DeliverToSubID(root.get("DeliverToSubID").asText()));
    }
    if (root.has("DeliverToLocationID")) {
      logon.getHeader().setField(new DeliverToLocationID(root.get("DeliverToLocationID").asText()));
    }
    if (root.has("PossDupFlag")) {
      logon.getHeader().setField(new PossDupFlag(root.get("PossDupFlag").asBoolean()));
    }
    if (root.has("PossResend")) {
      logon.getHeader().setField(new PossResend(root.get("PossResend").asBoolean()));
    }
    logon.getHeader().setField(new SendingTime(newLocalDateTime(root.get("SendingTime").asText())));
    if (root.has("OrigSendingTime")) {
      logon
          .getHeader()
          .setField(new OrigSendingTime(newLocalDateTime(root.get("OrigSendingTime").asText())));
    }
    if (root.has("XmlDataLen")) {
      logon.getHeader().setField(new XmlDataLen(root.get("XmlDataLen").asInt()));
    }
    if (root.has("XmlData")) {
      logon.getHeader().setField(new XmlData(root.get("XmlData").asText()));
    }
    if (root.has("MessageEncoding")) {
      logon.getHeader().setField(new MessageEncoding(root.get("MessageEncoding").asText()));
    }
    if (root.has("LastMsgSeqNumProcessed")) {
      logon
          .getHeader()
          .setField(new LastMsgSeqNumProcessed(root.get("LastMsgSeqNumProcessed").asInt()));
    }
    logon.setField(new EncryptMethod(root.get("EncryptMethod").asInt()));
    logon.setField(new HeartBtInt(root.get("HeartBtInt").asInt()));
    if (root.has("RawDataLength")) {
      logon.setField(new RawDataLength(root.get("RawDataLength").asInt()));
    }
    if (root.has("RawData")) {
      logon.setField(new RawData(root.get("RawData").asText()));
    }
    if (root.has("ResetSeqNumFlag")) {
      logon.setField(new ResetSeqNumFlag(root.get("ResetSeqNumFlag").asBoolean()));
    }
    if (root.has("NextExpectedMsgSeqNum")) {
      logon.setField(new NextExpectedMsgSeqNum(root.get("NextExpectedMsgSeqNum").asInt()));
    }
    if (root.has("MaxMessageSize")) {
      logon.setField(new MaxMessageSize(root.get("MaxMessageSize").asInt()));
    }
    if (root.has("TestMessageIndicator")) {
      logon.setField(new TestMessageIndicator(root.get("TestMessageIndicator").asBoolean()));
    }
    if (root.has("Username")) {
      logon.setField(new Username(root.get("Username").asText()));
    }
    if (root.has("Password")) {
      logon.setField(new Password(root.get("Password").asText()));
    }
    if (root.has("NoMsgTypes")) {
      JsonNode noMsgTypesNode = root.get("NoMsgTypes");
      for (JsonNode noMsgTypeNode : noMsgTypesNode) {
        Logon.NoMsgTypes group = new Logon.NoMsgTypes();
        if (noMsgTypeNode.has("RefMsgType")) {
          group.setField(new MsgType(noMsgTypeNode.get("RefMsgType").asText()));
        }
        if (noMsgTypeNode.has("MsgDirection")) {
          group.setField(new MsgDirection(noMsgTypeNode.get("MsgDirection").asText().charAt(0)));
        }
        logon.addGroup(group);
      }
    }
    if (root.has("SignatureLength")) {
      logon.getTrailer().setField(new SignatureLength(root.get("SignatureLength").asInt()));
    }
    if (root.has("Signature")) {
      logon.getTrailer().setField(new Signature(root.get("Signature").asText()));
    }
    logon.getTrailer().setField(new CheckSum(root.get("CheckSum").asText()));
    return logon;
  }
}
