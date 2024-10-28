package com.google.zxing.client.result;

import org.junit.Test;
import static org.junit.Assert.*;

public final class AddressBookParsedResultTestCase {

  @Test
  public void testAddressBookDocomo() {
    String mecard = "MECARD:N:John Doe;EMAIL:john.doe@example.com;URL:http://example.com;NOTE:Sample note;;";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, null, null,
      new String[]{"john.doe@example.com"}, null, null,
      "Sample note", null, null, null, null, null,
      new String[]{"http://example.com"}, null
    );
    assertEquals("John Doe", result.getNames()[0]);
    assertEquals("john.doe@example.com", result.getEmails()[0]);
    assertEquals("http://example.com", result.getURLs()[0]);
    assertEquals("Sample note", result.getNote());
  }

  @Test
  public void testAddressBookAU() {
    String au = "MEMORY:John Doe;TEL:1234567890;MEMO:Sample memo;;";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, new String[]{"1234567890"}, null,
      null, null, null, "Sample memo", null, null, null, null, null, null, null
    );
    assertEquals("John Doe", result.getNames()[0]);
    assertEquals("1234567890", result.getPhoneNumbers()[0]);
    assertEquals("Sample memo", result.getNote());
  }

  @Test
  public void testVCard() {
    String vcard = "BEGIN:VCARD\nFN:John Doe\nADR:123 Main St\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, null, null,
      null, null, null, null, new String[]{"123 Main St"}, null, null, null, null, null, null
    );
    assertEquals("John Doe", result.getNames()[0]);
    assertEquals("123 Main St", result.getAddresses()[0]);
  }

  @Test
  public void testVCardFullN() {
    String vcard = "BEGIN:VCARD\nN:Dr.;John;A.;Doe;Jr.\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"Dr. John A. Doe Jr."}, null, null, null, null,
      null, null, null, null, null, null, null, null, null, null, null
    );
    assertEquals("Dr. John A. Doe Jr.", result.getNames()[0]);
  }

  @Test
  public void testVCardFullN2() {
    String vcard = "BEGIN:VCARD\nN:John;Doe\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, null, null,
      null, null, null, null, null, null, null, null, null, null, null
    );
    assertEquals("John Doe", result.getNames()[0]);
  }

  @Test
  public void testVCardFullN3() {
    String vcard = "BEGIN:VCARD\nN:John\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John"}, null, null, null, null,
      null, null, null, null, null, null, null, null, null, null, null
    );
    assertEquals("John", result.getNames()[0]);
  }

  @Test
  public void testVCardCaseInsensitive() {
    String vcard = "BEGIN:VCARD\nfn:John Doe\nadr:123 Main St\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, null, null,
      null, null, null, null, new String[]{"123 Main St"}, null, null, null, null, null, null
    );
    assertEquals("John Doe", result.getNames()[0]);
    assertEquals("123 Main St", result.getAddresses()[0]);
  }

  @Test
  public void testEscapedVCard() {
    String vcard = "BEGIN:VCARD\nADR:123\\; Main St\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      null, null, null, null, null,
      null, null, null, null, new String[]{"123; Main St"}, null, null, null, null, null, null
    );
    assertEquals("123; Main St", result.getAddresses()[0]);
  }

  @Test
  public void testBizcard() {
    String bizcard = "BIZCARD:N:John Doe;X:Company;A:123 Main St;E:john.doe@example.com;T:1234567890;;";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, new String[]{"1234567890"}, null,
      new String[]{"john.doe@example.com"}, null, null, null, new String[]{"123 Main St"}, null,
      "Company", null, null, null, null
    );
    assertEquals("John Doe", result.getNames()[0]);
    assertEquals("Company", result.getOrg());
    assertEquals("123 Main St", result.getAddresses()[0]);
    assertEquals("john.doe@example.com", result.getEmails()[0]);
    assertEquals("1234567890", result.getPhoneNumbers()[0]);
  }

  @Test
  public void testSeveralAddresses() {
    String mecard = "MECARD:N:John Doe;ADR:123 Main St;ADR:456 Elm St;;";
    AddressBookParsedResult result = new AddressBookParsedResult(
      new String[]{"John Doe"}, null, null, null, null,
      null, null, null, null, new String[]{"123 Main St", "456 Elm St"}, null, null, null, null, null, null
    );
    assertArrayEquals(new String[]{"123 Main St", "456 Elm St"}, result.getAddresses());
  }

  @Test
  public void testQuotedPrintable() {
    String vcard = "BEGIN:VCARD\nADR;ENCODING=QUOTED-PRINTABLE:123=0A456 Main St\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      null, null, null, null, null,
      null, null, null, null, new String[]{"123\n456 Main St"}, null, null, null, null, null, null
    );
    assertEquals("123\n456 Main St", result.getAddresses()[0]);
  }

  @Test
  public void testVCardEscape() {
    String vcard = "BEGIN:VCARD\nNOTE:Line1\\nLine2\\;Line3\\\\Line4\\,Line5\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      null, null, null, null, null,
      null, null, null, "Line1\nLine2;Line3\\Line4,Line5", null, null, null, null, null, null, null
    );
    assertEquals("Line1\nLine2;Line3\\Line4,Line5", result.getNote());
  }

  @Test
  public void testVCardValueURI() {
    String vcard = "BEGIN:VCARD\nTEL;VALUE=uri:tel:+1234567890\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      null, null, null, new String[]{"+1234567890"}, null,
      null, null, null, null, null, null, null, null, null, null, null
    );
    assertEquals("+1234567890", result.getPhoneNumbers()[0]);
  }

  @Test
  public void testVCardTypes() {
    String vcard = "BEGIN:VCARD\nTEL;TYPE=home:+1234567890\nTEL;TYPE=work:+0987654321\nEND:VCARD";
    AddressBookParsedResult result = new AddressBookParsedResult(
      null, null, null, new String[]{"+1234567890", "+0987654321"}, new String[]{"home", "work"},
      null, null, null, null, null, null, null, null, null, null, null
    );
    assertArrayEquals(new String[]{"+1234567890", "+0987654321"}, result.getPhoneNumbers());
    assertArrayEquals(new String[]{"home", "work"}, result.getPhoneTypes());
  }
}// 1-2 14/14
