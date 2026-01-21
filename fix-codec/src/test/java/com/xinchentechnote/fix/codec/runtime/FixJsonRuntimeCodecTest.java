package com.xinchentechnote.fix.codec.runtime;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchentechnote.fix.codec.LogonCodec;
import com.xinchentechnote.fix.codec.utils.FileUtils;
import com.xinchentechnote.fix.parser.v2.FixXmlDomParser;
import org.junit.Test;
import quickfix.fix44.Logon;
import quickfix.fix44.Message;

public class FixJsonRuntimeCodecTest {

  @Test
  public void encode() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String content = FileUtils.readFileToStringFromClassPath("fix/FIX44.xml");
    FixXmlDomParser parser = new FixXmlDomParser();
    FixJsonRuntimeCodec codec = new FixJsonRuntimeCodec(parser.parseFromXml(content));
    String json = FileUtils.readFileToStringFromClassPath("json/Logon.ref.json");
    JsonNode root = objectMapper.readTree(json);
    Message decode = codec.decode(root);
    assertNotNull(decode);
    JsonNode encode = codec.encode(decode);
    assertEquals(root, encode);
  }

  @Test
  public void decode() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String content = FileUtils.readFileToStringFromClassPath("fix/FIX44.xml");
    FixXmlDomParser parser = new FixXmlDomParser();
    FixJsonRuntimeCodec codec = new FixJsonRuntimeCodec(parser.parseFromXml(content));
    String json = FileUtils.readFileToStringFromClassPath("json/Logon.ref.json");
    LogonCodec logonCodec = new LogonCodec();
    JsonNode root = objectMapper.readTree(json);
    Message decode = codec.decode(root);
    Logon logon = logonCodec.decode(root);
    assertEquals(
        decode.toString().replaceAll("\u0001", "\n"), logon.toString().replaceAll("\u0001", "\n"));
  }
}
