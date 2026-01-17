package com.xinchentechnote.fix.codec.reflect;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.codec.utils.FileUtils;
import org.junit.Test;
import quickfix.fix44.Message;

public class FixJsonRuntimeCodecTest {

  @Test
  public void encode() throws Exception {
    String content = FileUtils.readFileToStringFromClassPath("fix/FIX44.xml");
    FixJsonRuntimeCodec codec = new FixJsonRuntimeCodec(content);
    String json = FileUtils.readFileToStringFromClassPath("json/Logon.ref.json");
    Message decode = codec.decode(json);
    assertNotNull(decode);
    String encode = codec.encode(decode);
    assertEquals(json, encode);
  }
}
