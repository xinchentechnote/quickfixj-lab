package com.xinchentechnote.fix.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import quickfix.field.*;
import quickfix.fix44.*;
import quickfix.fix44.component.Instrument;
import quickfix.fix44.component.InstrumentLeg;
import quickfix.fix44.component.UnderlyingInstrument;

public class AdvertisementCodec implements FixJsonCodec<Advertisement> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String encode(Advertisement advertisement) throws Exception {
    ObjectNode root = MAPPER.createObjectNode();
    root.put("BeginString", advertisement.getHeader().getString(BeginString.FIELD));
    root.put("BodyLength", advertisement.getHeader().getInt(BodyLength.FIELD));
    root.put("MsgType", advertisement.getHeader().getString(MsgType.FIELD));
    root.put("SenderCompID", advertisement.getHeader().getString(SenderCompID.FIELD));
    root.put("TargetCompID", advertisement.getHeader().getString(TargetCompID.FIELD));
    if (advertisement.getHeader().isSetField(OnBehalfOfCompID.FIELD)) {
      root.put("OnBehalfOfCompID", advertisement.getHeader().getString(OnBehalfOfCompID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToCompID.FIELD)) {
      root.put("DeliverToCompID", advertisement.getHeader().getString(DeliverToCompID.FIELD));
    }
    if (advertisement.getHeader().isSetField(SecureDataLen.FIELD)) {
      root.put("SecureDataLen", advertisement.getHeader().getInt(SecureDataLen.FIELD));
    }
    if (advertisement.getHeader().isSetField(SecureData.FIELD)) {
      root.put("SecureData", advertisement.getHeader().getString(SecureData.FIELD));
    }
    root.put("MsgSeqNum", advertisement.getHeader().getInt(MsgSeqNum.FIELD));
    if (advertisement.getHeader().isSetField(SenderSubID.FIELD)) {
      root.put("SenderSubID", advertisement.getHeader().getString(SenderSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(SenderLocationID.FIELD)) {
      root.put("SenderLocationID", advertisement.getHeader().getString(SenderLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(TargetSubID.FIELD)) {
      root.put("TargetSubID", advertisement.getHeader().getString(TargetSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(TargetLocationID.FIELD)) {
      root.put("TargetLocationID", advertisement.getHeader().getString(TargetLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(OnBehalfOfSubID.FIELD)) {
      root.put("OnBehalfOfSubID", advertisement.getHeader().getString(OnBehalfOfSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(OnBehalfOfLocationID.FIELD)) {
      root.put(
          "OnBehalfOfLocationID", advertisement.getHeader().getString(OnBehalfOfLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToSubID.FIELD)) {
      root.put("DeliverToSubID", advertisement.getHeader().getString(DeliverToSubID.FIELD));
    }
    if (advertisement.getHeader().isSetField(DeliverToLocationID.FIELD)) {
      root.put(
          "DeliverToLocationID", advertisement.getHeader().getString(DeliverToLocationID.FIELD));
    }
    if (advertisement.getHeader().isSetField(PossDupFlag.FIELD)) {
      root.put("PossDupFlag", advertisement.getHeader().getBoolean(PossDupFlag.FIELD));
    }
    if (advertisement.getHeader().isSetField(PossResend.FIELD)) {
      root.put("PossResend", advertisement.getHeader().getBoolean(PossResend.FIELD));
    }
    root.put(
        "SendingTime", advertisement.getHeader().getUtcTimeStamp(SendingTime.FIELD).toString());
    if (advertisement.getHeader().isSetField(OrigSendingTime.FIELD)) {
      root.put(
          "OrigSendingTime",
          advertisement.getHeader().getUtcTimeStamp(OrigSendingTime.FIELD).toString());
    }
    if (advertisement.getHeader().isSetField(XmlDataLen.FIELD)) {
      root.put("XmlDataLen", advertisement.getHeader().getInt(XmlDataLen.FIELD));
    }
    if (advertisement.getHeader().isSetField(XmlData.FIELD)) {
      root.put("XmlData", advertisement.getHeader().getString(XmlData.FIELD));
    }
    if (advertisement.getHeader().isSetField(MessageEncoding.FIELD)) {
      root.put("MessageEncoding", advertisement.getHeader().getString(MessageEncoding.FIELD));
    }
    if (advertisement.getHeader().isSetField(LastMsgSeqNumProcessed.FIELD)) {
      root.put(
          "LastMsgSeqNumProcessed", advertisement.getHeader().getInt(LastMsgSeqNumProcessed.FIELD));
    }
    root.put("AdvId", advertisement.getString(AdvId.FIELD));
    root.put("AdvTransType", advertisement.getString(AdvTransType.FIELD));
    if (advertisement.isSetField(AdvRefID.FIELD)) {
      root.put("AdvRefID", advertisement.getString(AdvRefID.FIELD));
    }
    Instrument instrument = advertisement.getInstrument();
    ObjectNode instrumentNode = MAPPER.createObjectNode();
    if (instrument.isSetField(Symbol.FIELD)) {
      instrumentNode.put("Symbol", instrument.getString(Symbol.FIELD));
    }
    if (instrument.isSetField(SecurityID.FIELD)) {
      instrumentNode.put("SecurityID", instrument.getString(SecurityID.FIELD));
    }
    if (instrument.isSetField(SecurityIDSource.FIELD)) {
      instrumentNode.put("SecurityIDSource", instrument.getString(SecurityIDSource.FIELD));
    }
    if (instrument.isSetField(Product.FIELD)) {
      instrumentNode.put("Product", instrument.getInt(Product.FIELD));
    }
    if (!instrumentNode.isEmpty()) {
      root.set("Instrument", instrumentNode);
    }
    if (advertisement.isSetField(NoLegs.FIELD)) {
      ArrayNode legsNode = MAPPER.createArrayNode();
      Advertisement.NoLegs noLegs = new Advertisement.NoLegs();
      for (int i = 1; i <= advertisement.getGroupCount(NoLegs.FIELD); i++) {
        ObjectNode legNode = MAPPER.createObjectNode();
        advertisement.getGroup(i, noLegs);
        InstrumentLeg leg = noLegs.getInstrumentLeg();
        if (leg.isSetField(LegSymbol.FIELD)) {
          legNode.put("LegSymbol", leg.getString(LegSymbol.FIELD));
        }
        if (leg.isSetField(LegSecurityID.FIELD)) {
          legNode.put("LegSecurityID", leg.getString(LegSecurityID.FIELD));
        }
        if (leg.isSetField(LegSecurityIDSource.FIELD)) {
          legNode.put("LegSecurityIDSource", leg.getString(LegSecurityIDSource.FIELD));
        }
        if (leg.isSetField(LegRatioQty.FIELD)) {
          legNode.put("LegRatioQty", leg.getInt(LegRatioQty.FIELD));
        }
        ObjectNode instrumentLegNode = MAPPER.createObjectNode();
        instrumentLegNode.set("InstrumentLeg", legNode);
        legsNode.add(instrumentLegNode);
      }
      root.set("NoLegs", legsNode);
    }
    if (advertisement.isSetField(NoUnderlyings.FIELD)) {
      ArrayNode underlyingsNode = MAPPER.createArrayNode();
      Advertisement.NoUnderlyings noUnderlyings = new Advertisement.NoUnderlyings();
      for (int i = 1; i <= advertisement.getGroupCount(NoUnderlyings.FIELD); i++) {
        ObjectNode underlyingNode = MAPPER.createObjectNode();
        advertisement.getGroup(i, noUnderlyings);
        UnderlyingInstrument underlyingInstrument = noUnderlyings.getUnderlyingInstrument();
        if (underlyingInstrument.isSetField(UnderlyingSymbol.FIELD)) {
          underlyingNode.put(
              "UnderlyingSymbol", underlyingInstrument.getString(UnderlyingSymbol.FIELD));
        }
        if (underlyingInstrument.isSetField(UnderlyingSecurityID.FIELD)) {
          underlyingNode.put(
              "UnderlyingSecurityID", underlyingInstrument.getString(UnderlyingSecurityID.FIELD));
        }
        if (underlyingInstrument.isSetField(UnderlyingSecurityIDSource.FIELD)) {
          underlyingNode.put(
              "UnderlyingSecurityIDSource",
              underlyingInstrument.getString(UnderlyingSecurityIDSource.FIELD));
        }
        ObjectNode underlyingInstrumentNode = MAPPER.createObjectNode();
        underlyingInstrumentNode.set("UnderlyingInstrument", underlyingNode);
        underlyingsNode.add(underlyingInstrumentNode);
      }
      root.set("NoUnderlyings", underlyingsNode);
    }
    root.put("AdvSide", advertisement.getString(AdvSide.FIELD));
    root.put("Quantity", advertisement.getInt(Quantity.FIELD));
    if (advertisement.isSetField(QtyType.FIELD)) {
      root.put("QtyType", advertisement.getInt(QtyType.FIELD));
    }
    if (advertisement.isSetField(Price.FIELD)) {
      root.put("Price", advertisement.getDouble(Price.FIELD));
    }
    if (advertisement.isSetField(Currency.FIELD)) {
      root.put("Currency", advertisement.getString(Currency.FIELD));
    }
    if (advertisement.isSetField(TradeDate.FIELD)) {
      root.put("TradeDate", advertisement.getString(TradeDate.FIELD));
    }
    if (advertisement.isSetField(TransactTime.FIELD)) {
      root.put("TransactTime", advertisement.getUtcTimeStamp(TransactTime.FIELD).toString());
    }
    if (advertisement.isSetField(Text.FIELD)) {
      root.put("Text", advertisement.getString(Text.FIELD));
    }
    if (advertisement.isSetField(EncodedTextLen.FIELD)) {
      root.put("EncodedTextLen", advertisement.getInt(EncodedTextLen.FIELD));
    }
    if (advertisement.isSetField(EncodedText.FIELD)) {
      root.put("EncodedText", advertisement.getString(EncodedText.FIELD));
    }
    if (advertisement.isSetField(URLLink.FIELD)) {
      root.put("URLLink", advertisement.getString(URLLink.FIELD));
    }
    if (advertisement.isSetField(LastMkt.FIELD)) {
      root.put("LastMkt", advertisement.getString(LastMkt.FIELD));
    }
    if (advertisement.isSetField(TradingSessionID.FIELD)) {
      root.put("TradingSessionID", advertisement.getString(TradingSessionID.FIELD));
    }
    if (advertisement.isSetField(TradingSessionSubID.FIELD)) {
      root.put("TradingSessionSubID", advertisement.getString(TradingSessionSubID.FIELD));
    }

    if (advertisement.getTrailer().isSetField(SignatureLength.FIELD)) {
      root.put("SignatureLength", advertisement.getTrailer().getInt(SignatureLength.FIELD));
    }
    if (advertisement.getTrailer().isSetField(Signature.FIELD)) {
      root.put("Signature", advertisement.getTrailer().getString(Signature.FIELD));
    }
    root.put("CheckSum", advertisement.getTrailer().getString(CheckSum.FIELD));
    return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
  }

  @Override
  public Advertisement decode(String jsonString) throws Exception {
    JsonNode root = MAPPER.readTree(jsonString);
    Advertisement advertisement = new Advertisement();
    advertisement.getHeader().setField(new BeginString(root.get("BeginString").asText()));
    advertisement.getHeader().setField(new BodyLength(root.get("BodyLength").asInt()));
    advertisement.getHeader().setField(new MsgType(root.get("MsgType").asText()));
    advertisement.getHeader().setField(new SenderCompID(root.get("SenderCompID").asText()));
    advertisement.getHeader().setField(new TargetCompID(root.get("TargetCompID").asText()));
    if (root.has("OnBehalfOfCompID")) {
      advertisement
          .getHeader()
          .setField(new OnBehalfOfCompID(root.get("OnBehalfOfCompID").asText()));
    }
    if (root.has("DeliverToCompID")) {
      advertisement.getHeader().setField(new DeliverToCompID(root.get("DeliverToCompID").asText()));
    }
    if (root.has("SecureDataLen")) {
      advertisement.getHeader().setField(new SecureDataLen(root.get("SecureDataLen").asInt()));
    }
    if (root.has("SecureData")) {
      advertisement.getHeader().setField(new SecureData(root.get("SecureData").asText()));
    }
    advertisement.getHeader().setField(new MsgSeqNum(root.get("MsgSeqNum").asInt()));
    if (root.has("SenderSubID")) {
      advertisement.getHeader().setField(new SenderSubID(root.get("SenderSubID").asText()));
    }
    if (root.has("SenderLocationID")) {
      advertisement
          .getHeader()
          .setField(new SenderLocationID(root.get("SenderLocationID").asText()));
    }
    if (root.has("TargetSubID")) {
      advertisement.getHeader().setField(new TargetSubID(root.get("TargetSubID").asText()));
    }
    if (root.has("TargetLocationID")) {
      advertisement
          .getHeader()
          .setField(new TargetLocationID(root.get("TargetLocationID").asText()));
    }
    if (root.has("OnBehalfOfSubID")) {
      advertisement.getHeader().setField(new OnBehalfOfSubID(root.get("OnBehalfOfSubID").asText()));
    }
    if (root.has("OnBehalfOfLocationID")) {
      advertisement
          .getHeader()
          .setField(new OnBehalfOfLocationID(root.get("OnBehalfOfLocationID").asText()));
    }
    if (root.has("DeliverToSubID")) {
      advertisement.getHeader().setField(new DeliverToSubID(root.get("DeliverToSubID").asText()));
    }
    if (root.has("DeliverToLocationID")) {
      advertisement
          .getHeader()
          .setField(new DeliverToLocationID(root.get("DeliverToLocationID").asText()));
    }
    if (root.has("PossDupFlag")) {
      advertisement.getHeader().setField(new PossDupFlag(root.get("PossDupFlag").asBoolean()));
    }
    if (root.has("PossResend")) {
      advertisement.getHeader().setField(new PossResend(root.get("PossResend").asBoolean()));
    }
    advertisement
        .getHeader()
        .setField(new SendingTime(newLocalDateTime(root.get("SendingTime").asText())));
    if (root.has("OrigSendingTime")) {
      advertisement
          .getHeader()
          .setField(new OrigSendingTime(newLocalDateTime(root.get("OrigSendingTime").asText())));
    }
    if (root.has("XmlDataLen")) {
      advertisement.getHeader().setField(new XmlDataLen(root.get("XmlDataLen").asInt()));
    }
    if (root.has("XmlData")) {
      advertisement.getHeader().setField(new XmlData(root.get("XmlData").asText()));
    }
    if (root.has("MessageEncoding")) {
      advertisement.getHeader().setField(new MessageEncoding(root.get("MessageEncoding").asText()));
    }
    if (root.has("LastMsgSeqNumProcessed")) {
      advertisement
          .getHeader()
          .setField(new LastMsgSeqNumProcessed(root.get("LastMsgSeqNumProcessed").asInt()));
    }
    advertisement.setField(new AdvId(root.get("AdvId").asText()));
    advertisement.setField(new AdvTransType(root.get("AdvTransType").asText()));
    if (root.has("AdvRefID")) {
      advertisement.setField(new AdvRefID(root.get("AdvRefID").asText()));
    }
    advertisement.setField(new AdvSide(root.get("AdvSide").asText().charAt(0)));
    advertisement.setField(new Quantity(root.get("Quantity").asInt()));
    if (root.has("QtyType")) {
      advertisement.setField(new QtyType(root.get("QtyType").asInt()));
    }
    if (root.has("Price")) {
      advertisement.setField(new Price(root.get("Price").asDouble()));
    }
    if (root.has("Currency")) {
      advertisement.setField(new Currency(root.get("Currency").asText()));
    }
    if (root.has("TradeDate")) {
      advertisement.setField(new TradeDate(root.get("TradeDate").asText()));
    }
    if (root.has("TransactTime")) {
      advertisement.setField(new TransactTime(newLocalDateTime(root.get("TransactTime").asText())));
    }
    if (root.has("Text")) {
      advertisement.setField(new Text(root.get("Text").asText()));
    }
    if (root.has("EncodedTextLen")) {
      advertisement.setField(new EncodedTextLen(root.get("EncodedTextLen").asInt()));
    }
    if (root.has("EncodedText")) {
      advertisement.setField(new EncodedText(root.get("EncodedText").asText()));
    }
    if (root.has("URLLink")) {
      advertisement.setField(new URLLink(root.get("URLLink").asText()));
    }
    if (root.has("LastMkt")) {
      advertisement.setField(new LastMkt(root.get("LastMkt").asText()));
    }
    if (root.has("TradingSessionID")) {
      advertisement.setField(new TradingSessionID(root.get("TradingSessionID").asText()));
    }
    if (root.has("TradingSessionSubID")) {
      advertisement.setField(new TradingSessionSubID(root.get("TradingSessionSubID").asText()));
    }
    if (root.has("SignatureLength")) {
      advertisement.getTrailer().setField(new SignatureLength(root.get("SignatureLength").asInt()));
    }
    if (root.has("Signature")) {
      advertisement.getTrailer().setField(new Signature(root.get("Signature").asText()));
    }
    if (root.has("Instrument")) {
      JsonNode instrumentNode = root.get("Instrument");
      Instrument instrument = new Instrument();
      if (instrumentNode.has("Symbol")) {
        instrument.set(new Symbol(instrumentNode.get("Symbol").asText()));
      }
      if (instrumentNode.has("SecurityID")) {
        instrument.set(new SecurityID(instrumentNode.get("SecurityID").asText()));
      }
      if (instrumentNode.has("SecurityIDSource")) {
        instrument.set(new SecurityIDSource(instrumentNode.get("SecurityIDSource").asText()));
      }
      if (instrumentNode.has("Product")) {
        instrument.set(new Product(instrumentNode.get("Product").asInt()));
      }
      advertisement.set(instrument);
    }
    if (root.has("NoLegs")) {
      JsonNode legsNode = root.get("NoLegs");
      if (legsNode != null && legsNode.isArray()) {
        for (JsonNode legNode : legsNode) {
          Advertisement.NoLegs noLegs = new Advertisement.NoLegs();
          JsonNode instLegNode = legNode.get("InstrumentLeg");
          if (instLegNode != null && instLegNode.isObject()) {
            InstrumentLeg leg = new InstrumentLeg();
            if (instLegNode.has("LegSymbol")) {
              leg.set(new LegSymbol(instLegNode.get("LegSymbol").asText()));
            }

            if (instLegNode.has("LegSecurityID")) {
              leg.set(new LegSecurityID(instLegNode.get("LegSecurityID").asText()));
            }

            if (instLegNode.has("LegSecurityIDSource")) {
              leg.set(new LegSecurityIDSource(instLegNode.get("LegSecurityIDSource").asText()));
            }

            if (instLegNode.has("LegRatioQty")) {
              leg.set(new LegRatioQty(instLegNode.get("LegRatioQty").asInt()));
            }
            noLegs.set(leg);
          }
          advertisement.addGroup(noLegs);
        }
      }
      if (root.has("NoUnderlyings")) {
        JsonNode underlyingsNode = root.get("NoUnderlyings");
        if (underlyingsNode != null && underlyingsNode.isArray()) {
          for (JsonNode underlyingNode : underlyingsNode) {
            Advertisement.NoUnderlyings noUnderlyings = new Advertisement.NoUnderlyings();
            if (underlyingNode.has("UnderlyingInstrument")) {
              JsonNode underlyingInstrumentNode = underlyingNode.get("UnderlyingInstrument");
              UnderlyingInstrument underlyingInstrument = new UnderlyingInstrument();
              if (underlyingInstrumentNode.has("UnderlyingSymbol")) {
                underlyingInstrument.set(
                    new UnderlyingSymbol(
                        underlyingInstrumentNode.get("UnderlyingSymbol").asText()));
              }
              if (underlyingInstrumentNode.has("UnderlyingSecurityID")) {
                underlyingInstrument.set(
                    new UnderlyingSecurityID(
                        underlyingInstrumentNode.get("UnderlyingSecurityID").asText()));
              }
              if (underlyingInstrumentNode.has("UnderlyingSecurityIDSource")) {
                underlyingInstrument.set(
                    new UnderlyingSecurityIDSource(
                        underlyingInstrumentNode.get("UnderlyingSecurityIDSource").asText()));
              }
              noUnderlyings.set(underlyingInstrument);
            }
            advertisement.addGroup(noUnderlyings);
          }
        }
      }
    }
    advertisement.getTrailer().setField(new CheckSum(root.get("CheckSum").asText()));
    return advertisement;
  }
}
