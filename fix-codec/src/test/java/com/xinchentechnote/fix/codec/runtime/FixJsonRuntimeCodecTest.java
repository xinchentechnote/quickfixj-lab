package com.xinchentechnote.fix.codec.runtime;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.codec.LogonCodec;
import com.xinchentechnote.fix.codec.utils.FileUtils;
import com.xinchentechnote.fix.parser.v2.FixXmlDomParser;
import org.junit.Ignore;
import org.junit.Test;
import quickfix.fix44.Logon;
import quickfix.fix44.Message;

public class FixJsonRuntimeCodecTest {

  @Test
  public void encode() throws Exception {
    String content = FileUtils.readFileToStringFromClassPath("fix/FIX44.xml");
    FixXmlDomParser parser = new FixXmlDomParser();
    FixJsonRuntimeCodec codec = new FixJsonRuntimeCodec(parser.parseFromXml(content));
    String json = FileUtils.readFileToStringFromClassPath("json/Logon.ref.json");
    Message decode = codec.decode(json);
    assertNotNull(decode);
    String encode = codec.encode(decode);
    assertEquals(json, encode);
  }

  @Test
  @Ignore
  public void decode() throws Exception {
    String content = FileUtils.readFileToStringFromClassPath("fix/FIX44.xml");
    FixXmlDomParser parser = new FixXmlDomParser();
    FixJsonRuntimeCodec codec = new FixJsonRuntimeCodec(parser.parseFromXml(content));
    String json = FileUtils.readFileToStringFromClassPath("json/Logon.ref.json");
    LogonCodec logonCodec = new LogonCodec();
    Message decode = codec.decode(json);
    Logon logon = logonCodec.decode(json);
    assertEquals(
        decode.toString().replaceAll("\u0001", "\n"), logon.toString().replaceAll("\u0001", "\n"));
  }
}
