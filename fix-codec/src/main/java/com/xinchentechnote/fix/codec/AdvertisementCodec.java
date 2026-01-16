package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import quickfix.Group;
import quickfix.Message;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class AdvertisementCodec implements FixJsonCodec<Advertisement> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Advertisement advertisement) throws Exception {
    ObjectNode advertisementNode = MAPPER.createObjectNode();
    Message.Header header = advertisement.getHeader();
    Message.Trailer trailer = advertisement.getTrailer();
    advertisementNode.put("BeginString", header.getString(BeginString.FIELD));
    advertisementNode.put("BodyLength", header.getInt(BodyLength.FIELD));
    advertisementNode.put("MsgType", header.getString(MsgType.FIELD));
    advertisementNode.put("SenderCompID", header.getString(SenderCompID.FIELD));
    advertisementNode.put("TargetCompID", header.getString(TargetCompID.FIELD));
    if (header.isSetField(OnBehalfOfCompID.FIELD)) {
      advertisementNode.put("OnBehalfOfCompID", header.getString(OnBehalfOfCompID.FIELD));
    }
    if (header.isSetField(DeliverToCompID.FIELD)) {
      advertisementNode.put("DeliverToCompID", header.getString(DeliverToCompID.FIELD));
    }
    if (header.isSetField(SecureDataLen.FIELD)) {
      advertisementNode.put("SecureDataLen", header.getInt(SecureDataLen.FIELD));
    }
    if (header.isSetField(SecureData.FIELD)) {
      advertisementNode.put("SecureData", header.getString(SecureData.FIELD));
    }
    advertisementNode.put("MsgSeqNum", header.getInt(MsgSeqNum.FIELD));
    if (header.isSetField(SenderSubID.FIELD)) {
      advertisementNode.put("SenderSubID", header.getString(SenderSubID.FIELD));
    }
    if (header.isSetField(SenderLocationID.FIELD)) {
      advertisementNode.put("SenderLocationID", header.getString(SenderLocationID.FIELD));
    }
    if (header.isSetField(TargetSubID.FIELD)) {
      advertisementNode.put("TargetSubID", header.getString(TargetSubID.FIELD));
    }
    if (header.isSetField(TargetLocationID.FIELD)) {
      advertisementNode.put("TargetLocationID", header.getString(TargetLocationID.FIELD));
    }
    if (header.isSetField(OnBehalfOfSubID.FIELD)) {
      advertisementNode.put("OnBehalfOfSubID", header.getString(OnBehalfOfSubID.FIELD));
    }
    if (header.isSetField(OnBehalfOfLocationID.FIELD)) {
      advertisementNode.put("OnBehalfOfLocationID", header.getString(OnBehalfOfLocationID.FIELD));
    }
    if (header.isSetField(DeliverToSubID.FIELD)) {
      advertisementNode.put("DeliverToSubID", header.getString(DeliverToSubID.FIELD));
    }
    if (header.isSetField(DeliverToLocationID.FIELD)) {
      advertisementNode.put("DeliverToLocationID", header.getString(DeliverToLocationID.FIELD));
    }
    if (header.isSetField(PossDupFlag.FIELD)) {
      advertisementNode.put("PossDupFlag", header.getBoolean(PossDupFlag.FIELD));
    }
    if (header.isSetField(PossResend.FIELD)) {
      advertisementNode.put("PossResend", header.getBoolean(PossResend.FIELD));
    }
    advertisementNode.put("SendingTime", header.getUtcTimeStamp(SendingTime.FIELD).toString());
    if (header.isSetField(OrigSendingTime.FIELD)) {
      advertisementNode.put(
          "OrigSendingTime", header.getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (header.isSetField(XmlDataLen.FIELD)) {
      advertisementNode.put("XmlDataLen", header.getInt(XmlDataLen.FIELD));
    }
    if (header.isSetField(XmlData.FIELD)) {
      advertisementNode.put("XmlData", header.getString(XmlData.FIELD));
    }
    if (header.isSetField(MessageEncoding.FIELD)) {
      advertisementNode.put("MessageEncoding", header.getString(MessageEncoding.FIELD));
    }
    if (header.isSetField(LastMsgSeqNumProcessed.FIELD)) {
      advertisementNode.put("LastMsgSeqNumProcessed", header.getInt(LastMsgSeqNumProcessed.FIELD));
    }
    if (header.isSetField(NoHops.FIELD)) {
      Group advertisementNoHopsGroup =
          new Group(
              NoHops.FIELD,
              HopCompID.FIELD,
              new int[] {HopCompID.FIELD, HopSendingTime.FIELD, HopRefID.FIELD, 0});
      ArrayNode advertisementNoHopsNode = MAPPER.createArrayNode();
      for (int i = 1; i <= header.getGroupCount(NoHops.FIELD); i++) {
        header.getGroup(i, advertisementNoHopsGroup);
        ObjectNode advertisementNoHopsGroupNode = MAPPER.createObjectNode();
        if (advertisementNoHopsGroup.isSetField(HopCompID.FIELD)) {
          advertisementNoHopsGroupNode.put(
              "HopCompID", advertisementNoHopsGroup.getString(HopCompID.FIELD));
        }
        if (advertisementNoHopsGroup.isSetField(HopSendingTime.FIELD)) {
          advertisementNoHopsGroupNode.put(
              "HopSendingTime",
              advertisementNoHopsGroup.getUtcTimeStamp(HopSendingTime.FIELD).toString());
        }
        if (advertisementNoHopsGroup.isSetField(HopRefID.FIELD)) {
          advertisementNoHopsGroupNode.put(
              "HopRefID", advertisementNoHopsGroup.getInt(HopRefID.FIELD));
        }
        advertisementNoHopsNode.add(advertisementNoHopsGroupNode);
      }
      advertisementNode.put("NoHops", advertisementNoHopsNode);
    }
    advertisementNode.put("AdvId", advertisement.getString(AdvId.FIELD));
    advertisementNode.put("AdvTransType", advertisement.getString(AdvTransType.FIELD));
    if (advertisement.isSetField(AdvRefID.FIELD)) {
      advertisementNode.put("AdvRefID", advertisement.getString(AdvRefID.FIELD));
    }
    ObjectNode advertisementInstrumentNode = MAPPER.createObjectNode();
    if (advertisement.isSetField(Symbol.FIELD)) {
        advertisementInstrumentNode.put("Symbol", advertisement.getString(Symbol.FIELD));
    }
    if (advertisement.isSetField(SecurityID.FIELD)) {
        advertisementInstrumentNode.put("SecurityID", advertisement.getString(SecurityID.FIELD));
    }
    if (advertisement.isSetField(SecurityIDSource.FIELD)) {
        advertisementInstrumentNode.put("SecurityIDSource", advertisement.getString(SecurityIDSource.FIELD));
    }
    advertisementNode.set("Instrument", advertisementInstrumentNode);
    advertisementNode.put("AdvSide", advertisement.getString(AdvSide.FIELD));
    advertisementNode.put("Quantity", advertisement.getInt(Quantity.FIELD));
    if (advertisement.isSetField(QtyType.FIELD)) {
      advertisementNode.put("QtyType", advertisement.getInt(QtyType.FIELD));
    }
    if (advertisement.isSetField(Price.FIELD)) {
      advertisementNode.put("Price", advertisement.getDouble(Price.FIELD));
    }
    if (advertisement.isSetField(Currency.FIELD)) {
      advertisementNode.put("Currency", advertisement.getString(Currency.FIELD));
    }
    if (advertisement.isSetField(TradeDate.FIELD)) {
      advertisementNode.put("TradeDate", advertisement.getString(TradeDate.FIELD));
    }
    if (advertisement.isSetField(TransactTime.FIELD)) {
      advertisementNode.put(
          "TransactTime", advertisement.getUtcTimeStamp(TransactTime.FIELD).toString());
    }
    if (advertisement.isSetField(Text.FIELD)) {
      advertisementNode.put("Text", advertisement.getString(Text.FIELD));
    }
    if (advertisement.isSetField(EncodedTextLen.FIELD)) {
      advertisementNode.put("EncodedTextLen", advertisement.getInt(EncodedTextLen.FIELD));
    }
    if (advertisement.isSetField(EncodedText.FIELD)) {
      advertisementNode.put("EncodedText", advertisement.getString(EncodedText.FIELD));
    }
    if (advertisement.isSetField(URLLink.FIELD)) {
      advertisementNode.put("URLLink", advertisement.getString(URLLink.FIELD));
    }
    if (advertisement.isSetField(LastMkt.FIELD)) {
      advertisementNode.put("LastMkt", advertisement.getString(LastMkt.FIELD));
    }
    if (advertisement.isSetField(TradingSessionID.FIELD)) {
      advertisementNode.put("TradingSessionID", advertisement.getString(TradingSessionID.FIELD));
    }
    if (advertisement.isSetField(TradingSessionSubID.FIELD)) {
      advertisementNode.put(
          "TradingSessionSubID", advertisement.getString(TradingSessionSubID.FIELD));
    }
    if (trailer.isSetField(SignatureLength.FIELD)) {
      advertisementNode.put("SignatureLength", trailer.getInt(SignatureLength.FIELD));
    }
    if (trailer.isSetField(Signature.FIELD)) {
      advertisementNode.put("Signature", trailer.getString(Signature.FIELD));
    }
    advertisementNode.put("CheckSum", trailer.getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advertisementNode);
  }

  @Override
  public Advertisement decode(String jsonString) throws Exception {
    JsonNode advertisementNode = MAPPER.readTree(jsonString);
    Advertisement advertisement = new Advertisement();
    Message.Header header = advertisement.getHeader();
    Message.Trailer trailer = advertisement.getTrailer();
    header.setField(new BeginString(advertisementNode.get("BeginString").asText()));
    header.setField(new BodyLength(advertisementNode.get("BodyLength").asInt()));
    header.setField(new MsgType(advertisementNode.get("MsgType").asText()));
    header.setField(new SenderCompID(advertisementNode.get("SenderCompID").asText()));
    header.setField(new TargetCompID(advertisementNode.get("TargetCompID").asText()));
    if (advertisementNode.has("OnBehalfOfCompID")) {
      header.setField(new OnBehalfOfCompID(advertisementNode.get("OnBehalfOfCompID").asText()));
    }
    if (advertisementNode.has("DeliverToCompID")) {
      header.setField(new DeliverToCompID(advertisementNode.get("DeliverToCompID").asText()));
    }
    if (advertisementNode.has("SecureDataLen")) {
      header.setField(new SecureDataLen(advertisementNode.get("SecureDataLen").asInt()));
    }
    if (advertisementNode.has("SecureData")) {
      header.setField(new SecureData(advertisementNode.get("SecureData").asText()));
    }
    header.setField(new MsgSeqNum(advertisementNode.get("MsgSeqNum").asInt()));
    if (advertisementNode.has("SenderSubID")) {
      header.setField(new SenderSubID(advertisementNode.get("SenderSubID").asText()));
    }
    if (advertisementNode.has("SenderLocationID")) {
      header.setField(new SenderLocationID(advertisementNode.get("SenderLocationID").asText()));
    }
    if (advertisementNode.has("TargetSubID")) {
      header.setField(new TargetSubID(advertisementNode.get("TargetSubID").asText()));
    }
    if (advertisementNode.has("TargetLocationID")) {
      header.setField(new TargetLocationID(advertisementNode.get("TargetLocationID").asText()));
    }
    if (advertisementNode.has("OnBehalfOfSubID")) {
      header.setField(new OnBehalfOfSubID(advertisementNode.get("OnBehalfOfSubID").asText()));
    }
    if (advertisementNode.has("OnBehalfOfLocationID")) {
      header.setField(
          new OnBehalfOfLocationID(advertisementNode.get("OnBehalfOfLocationID").asText()));
    }
    if (advertisementNode.has("DeliverToSubID")) {
      header.setField(new DeliverToSubID(advertisementNode.get("DeliverToSubID").asText()));
    }
    if (advertisementNode.has("DeliverToLocationID")) {
      header.setField(
          new DeliverToLocationID(advertisementNode.get("DeliverToLocationID").asText()));
    }
    if (advertisementNode.has("PossDupFlag")) {
      header.setField(new PossDupFlag(advertisementNode.get("PossDupFlag").asBoolean()));
    }
    if (advertisementNode.has("PossResend")) {
      header.setField(new PossResend(advertisementNode.get("PossResend").asBoolean()));
    }
    header.setField(
        new SendingTime(newLocalDateTime(advertisementNode.get("SendingTime").asText())));
    if (advertisementNode.has("OrigSendingTime")) {
      header.setField(
          new OrigSendingTime(newLocalDateTime(advertisementNode.get("OrigSendingTime").asText())));
    }
    if (advertisementNode.has("XmlDataLen")) {
      header.setField(new XmlDataLen(advertisementNode.get("XmlDataLen").asInt()));
    }
    if (advertisementNode.has("XmlData")) {
      header.setField(new XmlData(advertisementNode.get("XmlData").asText()));
    }
    if (advertisementNode.has("MessageEncoding")) {
      header.setField(new MessageEncoding(advertisementNode.get("MessageEncoding").asText()));
    }
    if (advertisementNode.has("LastMsgSeqNumProcessed")) {
      header.setField(
          new LastMsgSeqNumProcessed(advertisementNode.get("LastMsgSeqNumProcessed").asInt()));
    }
    if (advertisementNode.has("NoHops")) {
      ArrayNode advertisementNoHopsGroupNodes = (ArrayNode) advertisementNode.get("NoHops");
      for (JsonNode advertisementNoHopsGroupNode : advertisementNoHopsGroupNodes) {
        Group advertisementNoHopsGroup =
            new Group(
                NoHops.FIELD,
                HopCompID.FIELD,
                new int[] {HopCompID.FIELD, HopSendingTime.FIELD, HopRefID.FIELD, 0});
        if (advertisementNoHopsGroupNode.has("HopCompID")) {
          advertisementNoHopsGroup.setField(
              new HopCompID(advertisementNoHopsGroupNode.get("HopCompID").asText()));
        }
        if (advertisementNoHopsGroupNode.has("HopSendingTime")) {
          advertisementNoHopsGroup.setField(
              new HopSendingTime(
                  newLocalDateTime(advertisementNoHopsGroupNode.get("HopSendingTime").asText())));
        }
        if (advertisementNoHopsGroupNode.has("HopRefID")) {
          advertisementNoHopsGroup.setField(
              new HopRefID(advertisementNoHopsGroupNode.get("HopRefID").asInt()));
        }
        header.addGroup(advertisementNoHopsGroup);
      }
    }
    advertisement.setField(new AdvId(advertisementNode.get("AdvId").asText()));
    advertisement.setField(new AdvTransType(advertisementNode.get("AdvTransType").asText()));
    if (advertisementNode.has("AdvRefID")) {
      advertisement.setField(new AdvRefID(advertisementNode.get("AdvRefID").asText()));
    }
    if (advertisementNode.has("Instrument")) {
      ObjectNode advertisementInstrumentNode = (ObjectNode) advertisementNode.get("Instrument");
      if (advertisementInstrumentNode.has("Symbol")) {
        advertisement.setField(new Symbol(advertisementInstrumentNode.get("Symbol").asText()));
      }
      if (advertisementInstrumentNode.has("SecurityID")) {
        advertisement.setField(new SecurityID(advertisementInstrumentNode.get("SecurityID").asText()));
      }
      if (advertisementInstrumentNode.has("SecurityIDSource")) {
        advertisement.setField(
            new SecurityIDSource(advertisementInstrumentNode.get("SecurityIDSource").asText()));
      }
    }
    advertisement.setField(new AdvSide(advertisementNode.get("AdvSide").asText().charAt(0)));
    advertisement.setField(new Quantity(advertisementNode.get("Quantity").asInt()));
    if (advertisementNode.has("QtyType")) {
      advertisement.setField(new QtyType(advertisementNode.get("QtyType").asInt()));
    }
    if (advertisementNode.has("Price")) {
      advertisement.setField(new Price(advertisementNode.get("Price").asDouble()));
    }
    if (advertisementNode.has("Currency")) {
      advertisement.setField(new Currency(advertisementNode.get("Currency").asText()));
    }
    if (advertisementNode.has("TradeDate")) {
      advertisement.setField(new TradeDate(advertisementNode.get("TradeDate").asText()));
    }
    if (advertisementNode.has("TransactTime")) {
      advertisement.setField(
          new TransactTime(newLocalDateTime(advertisementNode.get("TransactTime").asText())));
    }
    if (advertisementNode.has("Text")) {
      advertisement.setField(new Text(advertisementNode.get("Text").asText()));
    }
    if (advertisementNode.has("EncodedTextLen")) {
      advertisement.setField(new EncodedTextLen(advertisementNode.get("EncodedTextLen").asInt()));
    }
    if (advertisementNode.has("EncodedText")) {
      advertisement.setField(new EncodedText(advertisementNode.get("EncodedText").asText()));
    }
    if (advertisementNode.has("URLLink")) {
      advertisement.setField(new URLLink(advertisementNode.get("URLLink").asText()));
    }
    if (advertisementNode.has("LastMkt")) {
      advertisement.setField(new LastMkt(advertisementNode.get("LastMkt").asText()));
    }
    if (advertisementNode.has("TradingSessionID")) {
      advertisement.setField(
          new TradingSessionID(advertisementNode.get("TradingSessionID").asText()));
    }
    if (advertisementNode.has("TradingSessionSubID")) {
      advertisement.setField(
          new TradingSessionSubID(advertisementNode.get("TradingSessionSubID").asText()));
    }
    if (advertisementNode.has("SignatureLength")) {
      trailer.setField(new SignatureLength(advertisementNode.get("SignatureLength").asInt()));
    }
    if (advertisementNode.has("Signature")) {
      trailer.setField(new Signature(advertisementNode.get("Signature").asText()));
    }
    trailer.setField(new CheckSum(advertisementNode.get("CheckSum").asText()));
    return advertisement;
  }
}
