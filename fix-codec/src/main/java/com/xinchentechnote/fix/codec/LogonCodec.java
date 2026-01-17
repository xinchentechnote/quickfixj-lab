package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.Group;
import quickfix.Message;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class LogonCodec implements FixJsonCodec<Logon> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Logon logon) throws Exception {
    ObjectNode logonNode = MAPPER.createObjectNode();
    Message.Header header = logon.getHeader();
    Message.Trailer trailer = logon.getTrailer();
    logonNode.put("BeginString", header.getString(BeginString.FIELD));
    logonNode.put("BodyLength", header.getInt(BodyLength.FIELD));
    logonNode.put("MsgType", header.getString(MsgType.FIELD));
    logonNode.put("SenderCompID", header.getString(SenderCompID.FIELD));
    logonNode.put("TargetCompID", header.getString(TargetCompID.FIELD));
    if (header.isSetField(OnBehalfOfCompID.FIELD)) {
      logonNode.put("OnBehalfOfCompID", header.getString(OnBehalfOfCompID.FIELD));
    }
    if (header.isSetField(DeliverToCompID.FIELD)) {
      logonNode.put("DeliverToCompID", header.getString(DeliverToCompID.FIELD));
    }
    if (header.isSetField(SecureDataLen.FIELD)) {
      logonNode.put("SecureDataLen", header.getInt(SecureDataLen.FIELD));
    }
    if (header.isSetField(SecureData.FIELD)) {
      logonNode.put("SecureData", header.getString(SecureData.FIELD));
    }
    logonNode.put("MsgSeqNum", header.getInt(MsgSeqNum.FIELD));
    if (header.isSetField(SenderSubID.FIELD)) {
      logonNode.put("SenderSubID", header.getString(SenderSubID.FIELD));
    }
    if (header.isSetField(SenderLocationID.FIELD)) {
      logonNode.put("SenderLocationID", header.getString(SenderLocationID.FIELD));
    }
    if (header.isSetField(TargetSubID.FIELD)) {
      logonNode.put("TargetSubID", header.getString(TargetSubID.FIELD));
    }
    if (header.isSetField(TargetLocationID.FIELD)) {
      logonNode.put("TargetLocationID", header.getString(TargetLocationID.FIELD));
    }
    if (header.isSetField(OnBehalfOfSubID.FIELD)) {
      logonNode.put("OnBehalfOfSubID", header.getString(OnBehalfOfSubID.FIELD));
    }
    if (header.isSetField(OnBehalfOfLocationID.FIELD)) {
      logonNode.put("OnBehalfOfLocationID", header.getString(OnBehalfOfLocationID.FIELD));
    }
    if (header.isSetField(DeliverToSubID.FIELD)) {
      logonNode.put("DeliverToSubID", header.getString(DeliverToSubID.FIELD));
    }
    if (header.isSetField(DeliverToLocationID.FIELD)) {
      logonNode.put("DeliverToLocationID", header.getString(DeliverToLocationID.FIELD));
    }
    if (header.isSetField(PossDupFlag.FIELD)) {
      logonNode.put("PossDupFlag", header.getBoolean(PossDupFlag.FIELD) ? "Y" : "N");
    }
    if (header.isSetField(PossResend.FIELD)) {
      logonNode.put("PossResend", header.getBoolean(PossResend.FIELD) ? "Y" : "N");
    }
    logonNode.put("SendingTime", header.getUtcTimeStamp(SendingTime.FIELD).toString());
    if (header.isSetField(OrigSendingTime.FIELD)) {
      logonNode.put("OrigSendingTime", header.getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (header.isSetField(XmlDataLen.FIELD)) {
      logonNode.put("XmlDataLen", header.getInt(XmlDataLen.FIELD));
    }
    if (header.isSetField(XmlData.FIELD)) {
      logonNode.put("XmlData", header.getString(XmlData.FIELD));
    }
    if (header.isSetField(MessageEncoding.FIELD)) {
      logonNode.put("MessageEncoding", header.getString(MessageEncoding.FIELD));
    }
    if (header.isSetField(LastMsgSeqNumProcessed.FIELD)) {
      logonNode.put("LastMsgSeqNumProcessed", header.getInt(LastMsgSeqNumProcessed.FIELD));
    }
    if (header.isSetField(NoHops.FIELD)) {
      Group logonNoHopsGroup =
          new Group(
              NoHops.FIELD,
              HopCompID.FIELD,
              new int[] {HopCompID.FIELD, HopSendingTime.FIELD, HopRefID.FIELD, 0});
      ArrayNode logonNoHopsNode = MAPPER.createArrayNode();
      for (int i = 1; i <= header.getGroupCount(NoHops.FIELD); i++) {
        header.getGroup(i, logonNoHopsGroup);
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
      logonNode.set("NoHops", logonNoHopsNode);
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
      logonNode.put("ResetSeqNumFlag", logon.getBoolean(ResetSeqNumFlag.FIELD) ? "Y" : "N");
    }
    if (logon.isSetField(NextExpectedMsgSeqNum.FIELD)) {
      logonNode.put("NextExpectedMsgSeqNum", logon.getInt(NextExpectedMsgSeqNum.FIELD));
    }
    if (logon.isSetField(MaxMessageSize.FIELD)) {
      logonNode.put("MaxMessageSize", logon.getInt(MaxMessageSize.FIELD));
    }
    if (logon.isSetField(NoMsgTypes.FIELD)) {
      Group logonNoMsgTypesGroup =
          new Group(
              NoMsgTypes.FIELD,
              RefMsgType.FIELD,
              new int[] {RefMsgType.FIELD, MsgDirection.FIELD, 0});
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
      logonNode.set("NoMsgTypes", logonNoMsgTypesNode);
    }
    if (logon.isSetField(TestMessageIndicator.FIELD)) {
      logonNode.put(
          "TestMessageIndicator", logon.getBoolean(TestMessageIndicator.FIELD) ? "Y" : "N");
    }
    if (logon.isSetField(Username.FIELD)) {
      logonNode.put("Username", logon.getString(Username.FIELD));
    }
    if (logon.isSetField(Password.FIELD)) {
      logonNode.put("Password", logon.getString(Password.FIELD));
    }
    if (trailer.isSetField(SignatureLength.FIELD)) {
      logonNode.put("SignatureLength", trailer.getInt(SignatureLength.FIELD));
    }
    if (trailer.isSetField(Signature.FIELD)) {
      logonNode.put("Signature", trailer.getString(Signature.FIELD));
    }
    logonNode.put("CheckSum", trailer.getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(logonNode);
  }

  @Override
  public Logon decode(String jsonString) throws Exception {
    JsonNode logonNode = MAPPER.readTree(jsonString);
    Logon logon = new Logon();
    Message.Header header = logon.getHeader();
    Message.Trailer trailer = logon.getTrailer();
    header.setField(new BeginString(logonNode.get("BeginString").asText()));
    header.setField(new SenderCompID(logonNode.get("SenderCompID").asText()));
    header.setField(new TargetCompID(logonNode.get("TargetCompID").asText()));
    if (logonNode.has("OnBehalfOfCompID")) {
      header.setField(new OnBehalfOfCompID(logonNode.get("OnBehalfOfCompID").asText()));
    }
    if (logonNode.has("DeliverToCompID")) {
      header.setField(new DeliverToCompID(logonNode.get("DeliverToCompID").asText()));
    }
    if (logonNode.has("SecureDataLen")) {
      header.setField(new SecureDataLen(logonNode.get("SecureDataLen").asInt()));
    }
    if (logonNode.has("SecureData")) {
      header.setField(new SecureData(logonNode.get("SecureData").asText()));
    }
    header.setField(new MsgSeqNum(logonNode.get("MsgSeqNum").asInt()));
    if (logonNode.has("SenderSubID")) {
      header.setField(new SenderSubID(logonNode.get("SenderSubID").asText()));
    }
    if (logonNode.has("SenderLocationID")) {
      header.setField(new SenderLocationID(logonNode.get("SenderLocationID").asText()));
    }
    if (logonNode.has("TargetSubID")) {
      header.setField(new TargetSubID(logonNode.get("TargetSubID").asText()));
    }
    if (logonNode.has("TargetLocationID")) {
      header.setField(new TargetLocationID(logonNode.get("TargetLocationID").asText()));
    }
    if (logonNode.has("OnBehalfOfSubID")) {
      header.setField(new OnBehalfOfSubID(logonNode.get("OnBehalfOfSubID").asText()));
    }
    if (logonNode.has("OnBehalfOfLocationID")) {
      header.setField(new OnBehalfOfLocationID(logonNode.get("OnBehalfOfLocationID").asText()));
    }
    if (logonNode.has("DeliverToSubID")) {
      header.setField(new DeliverToSubID(logonNode.get("DeliverToSubID").asText()));
    }
    if (logonNode.has("DeliverToLocationID")) {
      header.setField(new DeliverToLocationID(logonNode.get("DeliverToLocationID").asText()));
    }
    if (logonNode.has("PossDupFlag")) {
      header.setField(new PossDupFlag(logonNode.get("PossDupFlag").asText().equals("Y")));
    }
    if (logonNode.has("PossResend")) {
      header.setField(new PossResend(logonNode.get("PossResend").asText().equals("Y")));
    }
    header.setField(new SendingTime(newLocalDateTime(logonNode.get("SendingTime").asText())));
    if (logonNode.has("OrigSendingTime")) {
      header.setField(
          new OrigSendingTime(newLocalDateTime(logonNode.get("OrigSendingTime").asText())));
    }
    if (logonNode.has("XmlDataLen")) {
      header.setField(new XmlDataLen(logonNode.get("XmlDataLen").asInt()));
    }
    if (logonNode.has("XmlData")) {
      header.setField(new XmlData(logonNode.get("XmlData").asText()));
    }
    if (logonNode.has("MessageEncoding")) {
      header.setField(new MessageEncoding(logonNode.get("MessageEncoding").asText()));
    }
    if (logonNode.has("LastMsgSeqNumProcessed")) {
      header.setField(new LastMsgSeqNumProcessed(logonNode.get("LastMsgSeqNumProcessed").asInt()));
    }
    if (logonNode.has("NoHops")) {
      ArrayNode logonNoHopsGroupNodes = (ArrayNode) logonNode.get("NoHops");
      for (JsonNode logonNoHopsGroupNode : logonNoHopsGroupNodes) {
        Group logonNoHopsGroup =
            new Group(
                NoHops.FIELD,
                HopCompID.FIELD,
                new int[] {HopCompID.FIELD, HopSendingTime.FIELD, HopRefID.FIELD, 0});
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
        header.addGroup(logonNoHopsGroup);
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
      logon.setField(new ResetSeqNumFlag(logonNode.get("ResetSeqNumFlag").asText().equals("Y")));
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
        Group logonNoMsgTypesGroup =
            new Group(
                NoMsgTypes.FIELD,
                RefMsgType.FIELD,
                new int[] {RefMsgType.FIELD, MsgDirection.FIELD, 0});
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
      logon.setField(
          new TestMessageIndicator(logonNode.get("TestMessageIndicator").asText().equals("Y")));
    }
    if (logonNode.has("Username")) {
      logon.setField(new Username(logonNode.get("Username").asText()));
    }
    if (logonNode.has("Password")) {
      logon.setField(new Password(logonNode.get("Password").asText()));
    }
    if (logonNode.has("SignatureLength")) {
      trailer.setField(new SignatureLength(logonNode.get("SignatureLength").asInt()));
    }
    if (logonNode.has("Signature")) {
      trailer.setField(new Signature(logonNode.get("Signature").asText()));
    }
    return logon;
  }
}
