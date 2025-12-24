package com.xinchentechnote.fix.codec;

import quickfix.Message;

public interface FixJsonCodec<T extends Message> {

  String encode(T message) throws Exception;

  T decode(String jsonString) throws Exception;
}
