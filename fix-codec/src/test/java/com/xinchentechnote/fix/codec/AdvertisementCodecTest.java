package com.xinchentechnote.fix.codec;

import static org.junit.Assert.*;

import com.xinchentechnote.fix.utils.FileUtils;
import org.junit.Test;
import quickfix.fix44.Advertisement;

public class AdvertisementCodecTest {

  @Test
  public void testDecodeAndEncode1() throws Exception {
    String json = FileUtils.readFileToString("json/Advertisement.json");
    AdvertisementCodec codec = new AdvertisementCodec();
    Advertisement decode = codec.decode(json);
    decode.toString();
    String encode = codec.encode(decode);
    assertEquals(json, encode);
  }
}
