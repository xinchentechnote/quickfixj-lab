package com.xinchentechnote.fix.codec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import quickfix.Message;

public interface FixJsonCodec<K, T extends Message> {

  DateTimeFormatter FIX_TS = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss.SSS");

  DateTimeFormatter ISO_TS = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  default LocalDateTime newLocalDateTime(String value) {
    if (value.contains("T")) {
      return LocalDateTime.parse(value, ISO_TS);
    }
    return LocalDateTime.parse(value, FIX_TS);
  }

  public default String toFixTimestamp(LocalDateTime ldt) {
    return FIX_TS.format(ldt);
  }

  default LocalDate newLocalDate(String dateString) {
    return LocalDate.parse(dateString);
  }

  default LocalTime newLocalTime(String timeString) {
    return LocalTime.parse(timeString);
  }

  K encode(T message) throws Exception;

  T decode(K json) throws Exception;
}
