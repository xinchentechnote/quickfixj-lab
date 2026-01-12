package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.*;

public class AdvertisementCodec implements FixJsonCodec<Advertisement> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Advertisement advertisement) throws Exception {
    ObjectNode advertisementNode = MAPPER.createObjectNode();
    advertisementNode.put("BeginString", advertisement.getHeader().getString(BeginString.FIELD));
    advertisementNode.put("BodyLength", advertisement.getHeader().getInt(BodyLength.FIELD));
    advertisementNode.put("MsgType", advertisement.getHeader().getString(MsgType.FIELD));
    advertisementNode.put("SenderCompID", advertisement.getHeader().getString(SenderCompID.FIELD));
    advertisementNode.put("TargetCompID", advertisement.getHeader().getString(TargetCompID.FIELD));
    if (advertisement.getHeader().isSetField(OnBehalfOfCompID.FIELD)) {
      advertisementNode.put(
          "OnBehalfOfCompID", advertisement.getHeader().getString(OnBehalfOfCompID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToCompID.FIELD)) {
      advertisementNode.put(
          "DeliverToCompID", advertisement.getHeader().getString(DeliverToCompID.FIELD));
    }
    if (advertisement.getHeader().isSetField(SecureDataLen.FIELD)) {
      advertisementNode.put("SecureDataLen", advertisement.getHeader().getInt(SecureDataLen.FIELD));
    }
    if (advertisement.getHeader().isSetField(SecureData.FIELD)) {
      advertisementNode.put("SecureData", advertisement.getHeader().getString(SecureData.FIELD));
    }
    advertisementNode.put("MsgSeqNum", advertisement.getHeader().getInt(MsgSeqNum.FIELD));
    if (advertisement.getHeader().isSetField(SenderSubID.FIELD)) {
      advertisementNode.put("SenderSubID", advertisement.getHeader().getString(SenderSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(SenderLocationID.FIELD)) {
      advertisementNode.put(
          "SenderLocationID", advertisement.getHeader().getString(SenderLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(TargetSubID.FIELD)) {
      advertisementNode.put("TargetSubID", advertisement.getHeader().getString(TargetSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(TargetLocationID.FIELD)) {
      advertisementNode.put(
          "TargetLocationID", advertisement.getHeader().getString(TargetLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(OnBehalfOfSubID.FIELD)) {
      advertisementNode.put(
          "OnBehalfOfSubID", advertisement.getHeader().getString(OnBehalfOfSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(OnBehalfOfLocationID.FIELD)) {
      advertisementNode.put(
          "OnBehalfOfLocationID", advertisement.getHeader().getString(OnBehalfOfLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToSubID.FIELD)) {
      advertisementNode.put(
          "DeliverToSubID", advertisement.getHeader().getString(DeliverToSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToLocationID.FIELD)) {
      advertisementNode.put(
          "DeliverToLocationID", advertisement.getHeader().getString(DeliverToLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(PossDupFlag.FIELD)) {
      advertisementNode.put("PossDupFlag", advertisement.getHeader().getBoolean(PossDupFlag.FIELD));
    }
    if (advertisement.getHeader().isSetField(PossResend.FIELD)) {
      advertisementNode.put("PossResend", advertisement.getHeader().getBoolean(PossResend.FIELD));
    }
    advertisementNode.put(
        "SendingTime", advertisement.getHeader().getUtcTimeStamp(SendingTime.FIELD).toString());
    if (advertisement.getHeader().isSetField(OrigSendingTime.FIELD)) {
      advertisementNode.put(
          "OrigSendingTime",
          advertisement.getHeader().getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (advertisement.getHeader().isSetField(XmlDataLen.FIELD)) {
      advertisementNode.put("XmlDataLen", advertisement.getHeader().getInt(XmlDataLen.FIELD));
    }
    if (advertisement.getHeader().isSetField(XmlData.FIELD)) {
      advertisementNode.put("XmlData", advertisement.getHeader().getString(XmlData.FIELD));
    }
    if (advertisement.getHeader().isSetField(MessageEncoding.FIELD)) {
      advertisementNode.put(
          "MessageEncoding", advertisement.getHeader().getString(MessageEncoding.FIELD));
    }
    if (advertisement.getHeader().isSetField(LastMsgSeqNumProcessed.FIELD)) {
      advertisementNode.put(
          "LastMsgSeqNumProcessed", advertisement.getHeader().getInt(LastMsgSeqNumProcessed.FIELD));
    }
    advertisementNode.put("AdvId", advertisement.getString(AdvId.FIELD));
    advertisementNode.put("AdvTransType", advertisement.getString(AdvTransType.FIELD));
    if (advertisement.isSetField(AdvRefID.FIELD)) {
      advertisementNode.put("AdvRefID", advertisement.getString(AdvRefID.FIELD));
    }
    ObjectNode advertisementInstrumentNode = MAPPER.createObjectNode();
    Instrument advertisementInstrument = advertisement.getInstrument();
    if (advertisementInstrument.isSetField(Symbol.FIELD)) {
      advertisementInstrumentNode.put("Symbol", advertisementInstrument.getString(Symbol.FIELD));
    }
    if (advertisementInstrument.isSetField(SecurityID.FIELD)) {
      advertisementInstrumentNode.put(
          "SecurityID", advertisementInstrument.getString(SecurityID.FIELD));
    }
    if (advertisementInstrument.isSetField(SecurityIDSource.FIELD)) {
      advertisementInstrumentNode.put(
          "SecurityIDSource", advertisementInstrument.getString(SecurityIDSource.FIELD));
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
    if (advertisement.getTrailer().isSetField(SignatureLength.FIELD)) {
      advertisementNode.put(
          "SignatureLength", advertisement.getTrailer().getInt(SignatureLength.FIELD));
    }
    if (advertisement.getTrailer().isSetField(Signature.FIELD)) {
      advertisementNode.put("Signature", advertisement.getTrailer().getString(Signature.FIELD));
    }
    advertisementNode.put("CheckSum", advertisement.getTrailer().getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advertisementNode);
  }

  @Override
  public Advertisement decode(String jsonString) throws Exception {
    JsonNode advertisementNode = MAPPER.readTree(jsonString);
    Advertisement advertisement = new Advertisement();
    advertisement
        .getHeader()
        .setField(new BeginString(advertisementNode.get("BeginString").asText()));
    advertisement.getHeader().setField(new BodyLength(advertisementNode.get("BodyLength").asInt()));
    advertisement.getHeader().setField(new MsgType(advertisementNode.get("MsgType").asText()));
    advertisement
        .getHeader()
        .setField(new SenderCompID(advertisementNode.get("SenderCompID").asText()));
    advertisement
        .getHeader()
        .setField(new TargetCompID(advertisementNode.get("TargetCompID").asText()));
    if (advertisementNode.has("OnBehalfOfCompID")) {
      advertisement
          .getHeader()
          .setField(new OnBehalfOfCompID(advertisementNode.get("OnBehalfOfCompID").asText()));
    }
    if (advertisementNode.has("DeliverToCompID")) {
      advertisement
          .getHeader()
          .setField(new DeliverToCompID(advertisementNode.get("DeliverToCompID").asText()));
    }
    if (advertisementNode.has("SecureDataLen")) {
      advertisement
          .getHeader()
          .setField(new SecureDataLen(advertisementNode.get("SecureDataLen").asInt()));
    }
    if (advertisementNode.has("SecureData")) {
      advertisement
          .getHeader()
          .setField(new SecureData(advertisementNode.get("SecureData").asText()));
    }
    advertisement.getHeader().setField(new MsgSeqNum(advertisementNode.get("MsgSeqNum").asInt()));
    if (advertisementNode.has("SenderSubID")) {
      advertisement
          .getHeader()
          .setField(new SenderSubID(advertisementNode.get("SenderSubID").asText()));
    }
    if (advertisementNode.has("SenderLocationID")) {
      advertisement
          .getHeader()
          .setField(new SenderLocationID(advertisementNode.get("SenderLocationID").asText()));
    }
    if (advertisementNode.has("TargetSubID")) {
      advertisement
          .getHeader()
          .setField(new TargetSubID(advertisementNode.get("TargetSubID").asText()));
    }
    if (advertisementNode.has("TargetLocationID")) {
      advertisement
          .getHeader()
          .setField(new TargetLocationID(advertisementNode.get("TargetLocationID").asText()));
    }
    if (advertisementNode.has("OnBehalfOfSubID")) {
      advertisement
          .getHeader()
          .setField(new OnBehalfOfSubID(advertisementNode.get("OnBehalfOfSubID").asText()));
    }
    if (advertisementNode.has("OnBehalfOfLocationID")) {
      advertisement
          .getHeader()
          .setField(
              new OnBehalfOfLocationID(advertisementNode.get("OnBehalfOfLocationID").asText()));
    }
    if (advertisementNode.has("DeliverToSubID")) {
      advertisement
          .getHeader()
          .setField(new DeliverToSubID(advertisementNode.get("DeliverToSubID").asText()));
    }
    if (advertisementNode.has("DeliverToLocationID")) {
      advertisement
          .getHeader()
          .setField(new DeliverToLocationID(advertisementNode.get("DeliverToLocationID").asText()));
    }
    if (advertisementNode.has("PossDupFlag")) {
      advertisement
          .getHeader()
          .setField(new PossDupFlag(advertisementNode.get("PossDupFlag").asBoolean()));
    }
    if (advertisementNode.has("PossResend")) {
      advertisement
          .getHeader()
          .setField(new PossResend(advertisementNode.get("PossResend").asBoolean()));
    }
    advertisement
        .getHeader()
        .setField(new SendingTime(newLocalDateTime(advertisementNode.get("SendingTime").asText())));
    if (advertisementNode.has("OrigSendingTime")) {
      advertisement
          .getHeader()
          .setField(
              new OrigSendingTime(
                  newLocalDateTime(advertisementNode.get("OrigSendingTime").asText())));
    }
    if (advertisementNode.has("XmlDataLen")) {
      advertisement
          .getHeader()
          .setField(new XmlDataLen(advertisementNode.get("XmlDataLen").asInt()));
    }
    if (advertisementNode.has("XmlData")) {
      advertisement.getHeader().setField(new XmlData(advertisementNode.get("XmlData").asText()));
    }
    if (advertisementNode.has("MessageEncoding")) {
      advertisement
          .getHeader()
          .setField(new MessageEncoding(advertisementNode.get("MessageEncoding").asText()));
    }
    if (advertisementNode.has("LastMsgSeqNumProcessed")) {
      advertisement
          .getHeader()
          .setField(
              new LastMsgSeqNumProcessed(advertisementNode.get("LastMsgSeqNumProcessed").asInt()));
    }
    advertisement.setField(new AdvId(advertisementNode.get("AdvId").asText()));
    advertisement.setField(new AdvTransType(advertisementNode.get("AdvTransType").asText()));
    if (advertisementNode.has("AdvRefID")) {
      advertisement.setField(new AdvRefID(advertisementNode.get("AdvRefID").asText()));
    }
    if (advertisementNode.has("Instrument")) {
      ObjectNode advertisementInstrumentNode = (ObjectNode) advertisementNode.get("Instrument");
      Instrument advertisementInstrument = advertisement.getInstrument();
      if (advertisementInstrumentNode.has("Symbol")) {
        advertisementInstrument.setField(
            new Symbol(advertisementInstrumentNode.get("Symbol").asText()));
      }
      if (advertisementInstrumentNode.has("SecurityID")) {
        advertisementInstrument.setField(
            new SecurityID(advertisementInstrumentNode.get("SecurityID").asText()));
      }
      if (advertisementInstrumentNode.has("SecurityIDSource")) {
        advertisementInstrument.setField(
            new SecurityIDSource(advertisementInstrumentNode.get("SecurityIDSource").asText()));
      }
      advertisement.set(advertisementInstrument);
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
      advertisement
          .getTrailer()
          .setField(new SignatureLength(advertisementNode.get("SignatureLength").asInt()));
    }
    if (advertisementNode.has("Signature")) {
      advertisement
          .getTrailer()
          .setField(new Signature(advertisementNode.get("Signature").asText()));
    }
    advertisement.getTrailer().setField(new CheckSum(advertisementNode.get("CheckSum").asText()));
    return advertisement;
  }
}
