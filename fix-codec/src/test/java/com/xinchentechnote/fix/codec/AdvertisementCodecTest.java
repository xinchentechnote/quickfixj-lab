package com.xinchentechnote.fix.codec;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchentechnote.fix.codec.utils.FileUtils;
import org.junit.Test;
import quickfix.fix44.Advertisement;

public class AdvertisementCodecTest {

  @Test
  public void testDecodeAndEncode1() throws Exception {
    String json = FileUtils.readFileToStringFromClassPath("json/Advertisement.json");
    AdvertisementCodec codec = new AdvertisementCodec();
    JsonNode advertisementNode = new ObjectMapper().readTree(json);
    Advertisement decode = codec.decode(advertisementNode);
    decode.toString();
    JsonNode encode = codec.encode(decode);
    assertEquals(advertisementNode, encode);
  }
}
