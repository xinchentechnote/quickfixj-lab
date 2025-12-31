package com.xinchentechnote.fix.codec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import quickfix.Message;

public interface FixJsonCodec<T extends Message> {
  default LocalDateTime newLocalDateTime(String dateTimeString) {
    return LocalDateTime.parse(dateTimeString);
  }

  default LocalDate newLocalDate(String dateString) {
    return LocalDate.parse(dateString);
  }

  default LocalTime newLocalTime(String timeString) {
    return LocalTime.parse(timeString);
  }

  String encode(T message) throws Exception;

  T decode(String jsonString) throws Exception;
}
