package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class LogonCodec implements FixJsonCodec<Logon> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Logon logon) throws Exception {
    ObjectNode logonNode = MAPPER.createObjectNode();
    logonNode.put("BeginString", logon.getHeader().getString(BeginString.FIELD));
    logonNode.put("BodyLength", logon.getHeader().getInt(BodyLength.FIELD));
    logonNode.put("MsgType", logon.getHeader().getString(MsgType.FIELD));
    logonNode.put("SenderCompID", logon.getHeader().getString(SenderCompID.FIELD));
    logonNode.put("TargetCompID", logon.getHeader().getString(TargetCompID.FIELD));
    if (logon.getHeader().isSetField(OnBehalfOfCompID.FIELD)) {
      logonNode.put("OnBehalfOfCompID", logon.getHeader().getString(OnBehalfOfCompID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToCompID.FIELD)) {
      logonNode.put("DeliverToCompID", logon.getHeader().getString(DeliverToCompID.FIELD));
    }
    if (logon.getHeader().isSetField(SecureDataLen.FIELD)) {
      logonNode.put("SecureDataLen", logon.getHeader().getInt(SecureDataLen.FIELD));
    }
    if (logon.getHeader().isSetField(SecureData.FIELD)) {
      logonNode.put("SecureData", logon.getHeader().getString(SecureData.FIELD));
    }
    logonNode.put("MsgSeqNum", logon.getHeader().getInt(MsgSeqNum.FIELD));
    if (logon.getHeader().isSetField(SenderSubID.FIELD)) {
      logonNode.put("SenderSubID", logon.getHeader().getString(SenderSubID.FIELD));
    }
    if (logon.getHeader().isSetField(SenderLocationID.FIELD)) {
      logonNode.put("SenderLocationID", logon.getHeader().getString(SenderLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(TargetSubID.FIELD)) {
      logonNode.put("TargetSubID", logon.getHeader().getString(TargetSubID.FIELD));
    }
    if (logon.getHeader().isSetField(TargetLocationID.FIELD)) {
      logonNode.put("TargetLocationID", logon.getHeader().getString(TargetLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(OnBehalfOfSubID.FIELD)) {
      logonNode.put("OnBehalfOfSubID", logon.getHeader().getString(OnBehalfOfSubID.FIELD));
    }
    if (logon.getHeader().isSetField(OnBehalfOfLocationID.FIELD)) {
      logonNode.put(
          "OnBehalfOfLocationID", logon.getHeader().getString(OnBehalfOfLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToSubID.FIELD)) {
      logonNode.put("DeliverToSubID", logon.getHeader().getString(DeliverToSubID.FIELD));
    }
    if (logon.getHeader().isSetField(DeliverToLocationID.FIELD)) {
      logonNode.put("DeliverToLocationID", logon.getHeader().getString(DeliverToLocationID.FIELD));
    }
    if (logon.getHeader().isSetField(PossDupFlag.FIELD)) {
      logonNode.put("PossDupFlag", logon.getHeader().getBoolean(PossDupFlag.FIELD));
    }
    if (logon.getHeader().isSetField(PossResend.FIELD)) {
      logonNode.put("PossResend", logon.getHeader().getBoolean(PossResend.FIELD));
    }
    logonNode.put("SendingTime", logon.getHeader().getUtcTimeStamp(SendingTime.FIELD).toString());
    if (logon.getHeader().isSetField(OrigSendingTime.FIELD)) {
      logonNode.put(
          "OrigSendingTime", logon.getHeader().getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (logon.getHeader().isSetField(XmlDataLen.FIELD)) {
      logonNode.put("XmlDataLen", logon.getHeader().getInt(XmlDataLen.FIELD));
    }
    if (logon.getHeader().isSetField(XmlData.FIELD)) {
      logonNode.put("XmlData", logon.getHeader().getString(XmlData.FIELD));
    }
    if (logon.getHeader().isSetField(MessageEncoding.FIELD)) {
      logonNode.put("MessageEncoding", logon.getHeader().getString(MessageEncoding.FIELD));
    }
    if (logon.getHeader().isSetField(LastMsgSeqNumProcessed.FIELD)) {
      logonNode.put(
          "LastMsgSeqNumProcessed", logon.getHeader().getInt(LastMsgSeqNumProcessed.FIELD));
    }
    if (logon.getHeader().isSetField(NoHops.FIELD)) {
      Message.Header.NoHops logonNoHopsGroup = new Message.Header.NoHops();
      ArrayNode logonNoHopsNode = MAPPER.createArrayNode();
      for (int i = 1; i <= logon.getGroupCount(NoHops.FIELD); i++) {
        logon.getGroup(i, logonNoHopsGroup);
        ObjectNode logonNoHopsGroupNode = MAPPER.createObjectNode();
        if (logonNoHopsGroup.isSetField(HopCompID.FIELD)) {
          logonNoHopsGroupNode.put("HopCompID", logonNoHopsGroup.getString(HopCompID.FIELD));
        }
        if (logonNoHopsGroup.isSetField(HopSendingTime.FIELD)) {
          logonNoHopsGroupNode.put(
              "HopSendingTime", logonNoHopsGroup.getUtcTimeStamp(HopSendingTime.FIELD).toString());
        }
        if (logonNoHopsGroup.isSetField(HopRefID.FIELD)) {
          logonNoHopsGroupNode.put("HopRefID", logonNoHopsGroup.getInt(HopRefID.FIELD));
        }
        logonNoHopsNode.add(logonNoHopsGroupNode);
      }
      logonNode.put("NoHops", logonNoHopsNode);
    }
    logonNode.put("EncryptMethod", logon.getInt(EncryptMethod.FIELD));
    logonNode.put("HeartBtInt", logon.getInt(HeartBtInt.FIELD));
    if (logon.isSetField(RawDataLength.FIELD)) {
      logonNode.put("RawDataLength", logon.getInt(RawDataLength.FIELD));
    }
    if (logon.isSetField(RawData.FIELD)) {
      logonNode.put("RawData", logon.getString(RawData.FIELD));
    }
    if (logon.isSetField(ResetSeqNumFlag.FIELD)) {
      logonNode.put("ResetSeqNumFlag", logon.getBoolean(ResetSeqNumFlag.FIELD));
    }
    if (logon.isSetField(NextExpectedMsgSeqNum.FIELD)) {
      logonNode.put("NextExpectedMsgSeqNum", logon.getInt(NextExpectedMsgSeqNum.FIELD));
    }
    if (logon.isSetField(MaxMessageSize.FIELD)) {
      logonNode.put("MaxMessageSize", logon.getInt(MaxMessageSize.FIELD));
    }
    if (logon.isSetField(NoMsgTypes.FIELD)) {
      Logon.NoMsgTypes logonNoMsgTypesGroup = new Logon.NoMsgTypes();
      ArrayNode logonNoMsgTypesNode = MAPPER.createArrayNode();
      for (int i = 1; i <= logon.getGroupCount(NoMsgTypes.FIELD); i++) {
        logon.getGroup(i, logonNoMsgTypesGroup);
        ObjectNode logonNoMsgTypesGroupNode = MAPPER.createObjectNode();
        if (logonNoMsgTypesGroup.isSetField(RefMsgType.FIELD)) {
          logonNoMsgTypesGroupNode.put(
              "RefMsgType", logonNoMsgTypesGroup.getString(RefMsgType.FIELD));
        }
        if (logonNoMsgTypesGroup.isSetField(MsgDirection.FIELD)) {
          logonNoMsgTypesGroupNode.put(
              "MsgDirection", logonNoMsgTypesGroup.getString(MsgDirection.FIELD));
        }
        logonNoMsgTypesNode.add(logonNoMsgTypesGroupNode);
      }
      logonNode.put("NoMsgTypes", logonNoMsgTypesNode);
    }
    if (logon.isSetField(TestMessageIndicator.FIELD)) {
      logonNode.put("TestMessageIndicator", logon.getBoolean(TestMessageIndicator.FIELD));
    }
    if (logon.isSetField(Username.FIELD)) {
      logonNode.put("Username", logon.getString(Username.FIELD));
    }
    if (logon.isSetField(Password.FIELD)) {
      logonNode.put("Password", logon.getString(Password.FIELD));
    }
    if (logon.getTrailer().isSetField(SignatureLength.FIELD)) {
      logonNode.put("SignatureLength", logon.getTrailer().getInt(SignatureLength.FIELD));
    }
    if (logon.getTrailer().isSetField(Signature.FIELD)) {
      logonNode.put("Signature", logon.getTrailer().getString(Signature.FIELD));
    }
    logonNode.put("CheckSum", logon.getTrailer().getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(logonNode);
  }

  @Override
  public Logon decode(String jsonString) throws Exception {
    JsonNode logonNode = MAPPER.readTree(jsonString);
    Logon logon = new Logon();
    logon.getHeader().setField(new BeginString(logonNode.get("BeginString").asText()));
    logon.getHeader().setField(new BodyLength(logonNode.get("BodyLength").asInt()));
    logon.getHeader().setField(new MsgType(logonNode.get("MsgType").asText()));
    logon.getHeader().setField(new SenderCompID(logonNode.get("SenderCompID").asText()));
    logon.getHeader().setField(new TargetCompID(logonNode.get("TargetCompID").asText()));
    if (logonNode.has("OnBehalfOfCompID")) {
      logon.getHeader().setField(new OnBehalfOfCompID(logonNode.get("OnBehalfOfCompID").asText()));
    }
    if (logonNode.has("DeliverToCompID")) {
      logon.getHeader().setField(new DeliverToCompID(logonNode.get("DeliverToCompID").asText()));
    }
    if (logonNode.has("SecureDataLen")) {
      logon.getHeader().setField(new SecureDataLen(logonNode.get("SecureDataLen").asInt()));
    }
    if (logonNode.has("SecureData")) {
      logon.getHeader().setField(new SecureData(logonNode.get("SecureData").asText()));
    }
    logon.getHeader().setField(new MsgSeqNum(logonNode.get("MsgSeqNum").asInt()));
    if (logonNode.has("SenderSubID")) {
      logon.getHeader().setField(new SenderSubID(logonNode.get("SenderSubID").asText()));
    }
    if (logonNode.has("SenderLocationID")) {
      logon.getHeader().setField(new SenderLocationID(logonNode.get("SenderLocationID").asText()));
    }
    if (logonNode.has("TargetSubID")) {
      logon.getHeader().setField(new TargetSubID(logonNode.get("TargetSubID").asText()));
    }
    if (logonNode.has("TargetLocationID")) {
      logon.getHeader().setField(new TargetLocationID(logonNode.get("TargetLocationID").asText()));
    }
    if (logonNode.has("OnBehalfOfSubID")) {
      logon.getHeader().setField(new OnBehalfOfSubID(logonNode.get("OnBehalfOfSubID").asText()));
    }
    if (logonNode.has("OnBehalfOfLocationID")) {
      logon
          .getHeader()
          .setField(new OnBehalfOfLocationID(logonNode.get("OnBehalfOfLocationID").asText()));
    }
    if (logonNode.has("DeliverToSubID")) {
      logon.getHeader().setField(new DeliverToSubID(logonNode.get("DeliverToSubID").asText()));
    }
    if (logonNode.has("DeliverToLocationID")) {
      logon
          .getHeader()
          .setField(new DeliverToLocationID(logonNode.get("DeliverToLocationID").asText()));
    }
    if (logonNode.has("PossDupFlag")) {
      logon.getHeader().setField(new PossDupFlag(logonNode.get("PossDupFlag").asBoolean()));
    }
    if (logonNode.has("PossResend")) {
      logon.getHeader().setField(new PossResend(logonNode.get("PossResend").asBoolean()));
    }
    logon
        .getHeader()
        .setField(new SendingTime(newLocalDateTime(logonNode.get("SendingTime").asText())));
    if (logonNode.has("OrigSendingTime")) {
      logon
          .getHeader()
          .setField(
              new OrigSendingTime(newLocalDateTime(logonNode.get("OrigSendingTime").asText())));
    }
    if (logonNode.has("XmlDataLen")) {
      logon.getHeader().setField(new XmlDataLen(logonNode.get("XmlDataLen").asInt()));
    }
    if (logonNode.has("XmlData")) {
      logon.getHeader().setField(new XmlData(logonNode.get("XmlData").asText()));
    }
    if (logonNode.has("MessageEncoding")) {
      logon.getHeader().setField(new MessageEncoding(logonNode.get("MessageEncoding").asText()));
    }
    if (logonNode.has("LastMsgSeqNumProcessed")) {
      logon
          .getHeader()
          .setField(new LastMsgSeqNumProcessed(logonNode.get("LastMsgSeqNumProcessed").asInt()));
    }
    if (logonNode.has("NoHops")) {
      ArrayNode logonNoHopsGroupNodes = (ArrayNode) logonNode.get("NoHops");
      for (JsonNode logonNoHopsGroupNode : logonNoHopsGroupNodes) {
        Message.Header.NoHops logonNoHopsGroup = new Message.Header.NoHops();
        if (logonNoHopsGroupNode.has("HopCompID")) {
          logonNoHopsGroup.setField(new HopCompID(logonNoHopsGroupNode.get("HopCompID").asText()));
        }
        if (logonNoHopsGroupNode.has("HopSendingTime")) {
          logonNoHopsGroup.setField(
              new HopSendingTime(
                  newLocalDateTime(logonNoHopsGroupNode.get("HopSendingTime").asText())));
        }
        if (logonNoHopsGroupNode.has("HopRefID")) {
          logonNoHopsGroup.setField(new HopRefID(logonNoHopsGroupNode.get("HopRefID").asInt()));
        }
        logon.addGroup(logonNoHopsGroup);
      }
    }
    logon.setField(new EncryptMethod(logonNode.get("EncryptMethod").asInt()));
    logon.setField(new HeartBtInt(logonNode.get("HeartBtInt").asInt()));
    if (logonNode.has("RawDataLength")) {
      logon.setField(new RawDataLength(logonNode.get("RawDataLength").asInt()));
    }
    if (logonNode.has("RawData")) {
      logon.setField(new RawData(logonNode.get("RawData").asText()));
    }
    if (logonNode.has("ResetSeqNumFlag")) {
      logon.setField(new ResetSeqNumFlag(logonNode.get("ResetSeqNumFlag").asBoolean()));
    }
    if (logonNode.has("NextExpectedMsgSeqNum")) {
      logon.setField(new NextExpectedMsgSeqNum(logonNode.get("NextExpectedMsgSeqNum").asInt()));
    }
    if (logonNode.has("MaxMessageSize")) {
      logon.setField(new MaxMessageSize(logonNode.get("MaxMessageSize").asInt()));
    }
    if (logonNode.has("NoMsgTypes")) {
      ArrayNode logonNoMsgTypesGroupNodes = (ArrayNode) logonNode.get("NoMsgTypes");
      for (JsonNode logonNoMsgTypesGroupNode : logonNoMsgTypesGroupNodes) {
        Logon.NoMsgTypes logonNoMsgTypesGroup = new Logon.NoMsgTypes();
        if (logonNoMsgTypesGroupNode.has("RefMsgType")) {
          logonNoMsgTypesGroup.setField(
              new RefMsgType(logonNoMsgTypesGroupNode.get("RefMsgType").asText()));
        }
        if (logonNoMsgTypesGroupNode.has("MsgDirection")) {
          logonNoMsgTypesGroup.setField(
              new MsgDirection(logonNoMsgTypesGroupNode.get("MsgDirection").asText().charAt(0)));
        }
        logon.addGroup(logonNoMsgTypesGroup);
      }
    }
    if (logonNode.has("TestMessageIndicator")) {
      logon.setField(new TestMessageIndicator(logonNode.get("TestMessageIndicator").asBoolean()));
    }
    if (logonNode.has("Username")) {
      logon.setField(new Username(logonNode.get("Username").asText()));
    }
    if (logonNode.has("Password")) {
      logon.setField(new Password(logonNode.get("Password").asText()));
    }
    if (logonNode.has("SignatureLength")) {
      logon.getTrailer().setField(new SignatureLength(logonNode.get("SignatureLength").asInt()));
    }
    if (logonNode.has("Signature")) {
      logon.getTrailer().setField(new Signature(logonNode.get("Signature").asText()));
    }
    logon.getTrailer().setField(new CheckSum(logonNode.get("CheckSum").asText()));
    return logon;
  }
}
