package com.xinchentechnote.fix.codec;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchentechnote.fix.codec.utils.FileUtils;
import java.time.LocalDateTime;
import org.junit.Test;
import quickfix.fix44.Logon;

public class LogonCodecTest {

  @Test
  public void testDecodeAndEncode1() throws Exception {

    String json = FileUtils.readFileToStringFromClassPath("json/Logon.simple.json");
    LogonCodec codec = new LogonCodec();
    JsonNode logonNode = new ObjectMapper().readTree(json);
    Logon decode = codec.decode(logonNode);
    decode.toString();
    JsonNode encode = codec.encode(decode);
    assertEquals(logonNode, encode);
  }

  @Test
  public void testDecodeAndEncode2() throws Exception {

    String json = FileUtils.readFileToStringFromClassPath("json/Logon.json");
    LogonCodec codec = new LogonCodec();
    JsonNode logonNode = new ObjectMapper().readTree(json);
    Logon decode = codec.decode(logonNode);
    decode.toString();
    JsonNode encode = codec.encode(decode);
    assertEquals(logonNode, encode);
  }

  @Test
  public void testEncodeAndDecode() throws Exception {
    Logon logon = new Logon();

    logon.getHeader().setString(8, "FIX.4.4");
    logon.getHeader().setString(9, "1");
    logon.getHeader().setString(35, "A");
    logon.getHeader().setString(49, "CLIENT");
    logon.getHeader().setUtcTimeStamp(52, LocalDateTime.now());
    logon.getHeader().setString(56, "SERVER");
    logon.getHeader().setInt(34, 1);

    logon.setInt(98, 0);
    logon.setInt(108, 30);
    logon.getTrailer().setString(10, "1");

    LogonCodec codec = new LogonCodec();
    JsonNode json = codec.encode(logon);
    Logon decoded = codec.decode(json);

    assertEquals("FIX.4.4", decoded.getHeader().getString(8));
    assertEquals("A", decoded.getHeader().getString(35));
    assertEquals("CLIENT", decoded.getHeader().getString(49));
    assertEquals("SERVER", decoded.getHeader().getString(56));
    assertEquals(1, decoded.getHeader().getInt(34));

    assertEquals(0, decoded.getInt(98));
    assertEquals(30, decoded.getInt(108));
  }
}
