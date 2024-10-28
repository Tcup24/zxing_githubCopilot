package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import org.junit.Test;
import static org.junit.Assert.*;

public class QRCodeTestCase {

  @Test
  public void testQRCodeProperties() {
    QRCode qrCode = new QRCode();
    qrCode.setMode(Mode.BYTE);
    qrCode.setECLevel(ErrorCorrectionLevel.H);
    qrCode.setVersion(Version.getVersionForNumber(7));
    qrCode.setMaskPattern(3);

    ByteMatrix matrix = new ByteMatrix(21, 21);
    qrCode.setMatrix(matrix);

    assertEquals(Mode.BYTE, qrCode.getMode());
    assertEquals(ErrorCorrectionLevel.H, qrCode.getECLevel());
    assertEquals(Version.getVersionForNumber(7), qrCode.getVersion());
    assertEquals(3, qrCode.getMaskPattern());
    assertEquals(matrix, qrCode.getMatrix());
  }

  @Test
  public void testToString1() {
    QRCode qrCode = new QRCode();
    String expectedString = "<<\n mode: null\n ecLevel: null\n version: null\n maskPattern: -1\n matrix: null\n>>\n";
    assertEquals(expectedString, qrCode.toString());
  }

  @Test
  public void testToString2() {
    QRCode qrCode = new QRCode();
    qrCode.setMode(Mode.BYTE);
    qrCode.setECLevel(ErrorCorrectionLevel.H);
    qrCode.setVersion(Version.getVersionForNumber(7));
    qrCode.setMaskPattern(3);

    ByteMatrix matrix = new ByteMatrix(21, 21);
    qrCode.setMatrix(matrix);

    String expectedString = "<<" +
      "\n mode: BYTE" +
      "\n ecLevel: H" +
      "\n version: 7" +
      "\n maskPattern: 3" +
      "\n matrix:\n" + matrix.toString() +
      ">>\n";

    String actualString = qrCode.toString();
    assertEquals(expectedString, actualString);
  }

  @Test
  public void testIsValidMaskPattern() {
    assertTrue(QRCode.isValidMaskPattern(0));
    assertTrue(QRCode.isValidMaskPattern(1));
    assertTrue(QRCode.isValidMaskPattern(2));
    assertTrue(QRCode.isValidMaskPattern(3));
    assertTrue(QRCode.isValidMaskPattern(4));
    assertTrue(QRCode.isValidMaskPattern(5));
    assertTrue(QRCode.isValidMaskPattern(6));
    assertTrue(QRCode.isValidMaskPattern(7));
    assertFalse(QRCode.isValidMaskPattern(-1));
    assertFalse(QRCode.isValidMaskPattern(8));
  }
}//1-2 4/4
