package com.xinchentechnote.fix.codec.reflect;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.utils.FileUtils;
import org.junit.Test;
import quickfix.fix44.Message;

public class FixJsonReflectCodecTest {

  @Test
  public void encode() throws Exception {
    FixJsonReflectCodec codec = new FixJsonReflectCodec("src/test/resources/fix/FIX44.xml");
    String json = FileUtils.readFileToString("json/Logon.ref.json");
    Message decode = codec.decode(json);
    assertNotNull(decode);
    String encode = codec.encode(decode);
    assertEquals(json, encode);
  }
}
