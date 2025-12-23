package com.xinchentechnote.fix.codec;

import org.junit.Test;
import quickfix.fix44.Logon;

import static org.junit.Assert.*;

public class LogonCodecTest {

    @Test
    public void testEncodeAndDecode() throws Exception {
        Logon logon = new Logon();

        logon.getHeader().setString(8, "FIX.4.4");
        logon.getHeader().setString(35, "A");
        logon.getHeader().setString(49, "CLIENT");
        logon.getHeader().setString(56, "SERVER");
        logon.getHeader().setInt(34, 1);

        logon.setInt(98, 0);
        logon.setInt(108, 30);

        LogonCodec codec = new LogonCodec();

        String json = codec.encode(logon);
        System.out.println(json);

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
