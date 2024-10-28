package com.google.zxing.client.result;

import org.junit.Test;
import static org.junit.Assert.*;

public final class WifiParsedResultTestCase {

  @Test
  public void testNoPassword() {
    String wifiConfig1 = "WIFI:S:NoPassword;;";
    WifiParsedResult result1 = new WifiParsedResult(null, "NoPassword", null);
    assertEquals("NoPassword", result1.getSsid());
    assertNull(result1.getPassword());

    String wifiConfig2 = "WIFI:S:No Password;;";
    WifiParsedResult result2 = new WifiParsedResult(null, "No Password", null);
    assertEquals("No Password", result2.getSsid());
    assertNull(result2.getPassword());
  }

  @Test
  public void testWep() {
    String wifiConfig1 = "WIFI:T:WEP;S:TenChars;P:1234567890;;";
    WifiParsedResult result1 = new WifiParsedResult("WEP", "TenChars", "1234567890");
    assertEquals("TenChars", result1.getSsid());
    assertEquals("1234567890", result1.getPassword());

    String wifiConfig2 = "WIFI:T:WEP;S:TenChars;P:12345\\;67890;;";
    WifiParsedResult result2 = new WifiParsedResult("WEP", "TenChars", "12345;67890");
    assertEquals("TenChars", result2.getSsid());
    assertEquals("12345;67890", result2.getPassword());

    String wifiConfig3 = "WIFI:T:WEP;S:TenChars;P:12\\;34\\:56;;";
    WifiParsedResult result3 = new WifiParsedResult("WEP", "TenChars", "12;34:56");
    assertEquals("TenChars", result3.getSsid());
    assertEquals("12;34:56", result3.getPassword());

    String wifiConfig4 = "WIFI:T:WEP;S:TenChars;P:nonhexpassword;;";
    WifiParsedResult result4 = new WifiParsedResult("WEP", "TenChars", "nonhexpassword");
    assertEquals("TenChars", result4.getSsid());
    assertEquals("nonhexpassword", result4.getPassword());
  }

  @Test
  public void testWpa() {
    String wifiConfig1 = "WIFI:T:WPA;S:TenChars;P:password123;;";
    WifiParsedResult result1 = new WifiParsedResult("WPA", "TenChars", "password123");
    assertEquals("TenChars", result1.getSsid());
    assertEquals("password123", result1.getPassword());

    String wifiConfig2 = "WIFI:T:WPA;S:TenChars;P:pass word;;";
    WifiParsedResult result2 = new WifiParsedResult("WPA", "TenChars", "pass word");
    assertEquals("TenChars", result2.getSsid());
    assertEquals("pass word", result2.getPassword());

    String wifiConfig3 = "WIFI:T:WPA;S:TenChars;P:pass\\;word;;";
    WifiParsedResult result3 = new WifiParsedResult("WPA", "TenChars", "pass;word");
    assertEquals("TenChars", result3.getSsid());
    assertEquals("pass;word", result3.getPassword());
  }

  @Test
  public void testEscape() {
    String wifiConfig1 = "WIFI:S:Ten\\;Chars;P:pass\\;word;;";
    WifiParsedResult result1 = new WifiParsedResult(null, "Ten;Chars", "pass;word");
    assertEquals("Ten;Chars", result1.getSsid());
    assertEquals("pass;word", result1.getPassword());

    String wifiConfig2 = "WIFI:S:Ten\\:Chars;P:pass\\:word;;";
    WifiParsedResult result2 = new WifiParsedResult(null, "Ten:Chars", "pass:word");
    assertEquals("Ten:Chars", result2.getSsid());
    assertEquals("pass:word", result2.getPassword());
  }
} // 4  4/4
