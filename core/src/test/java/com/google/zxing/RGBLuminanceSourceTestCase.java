package com.google.zxing;

import static org.junit.jupiter.api.Assertions.*;

import com.google.zxing.RGBLuminanceSource;
import org.junit.jupiter.api.Test;

class RGBLuminanceSourceTestCase {


  @Test
  void testMatrix() {
    int[] pixels = {
      0xFF000000, 0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF,
      0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF, 0xFF000000
    };
    RGBLuminanceSource source = new RGBLuminanceSource(4, 2, pixels);
    byte[] matrix = source.getMatrix();
    byte[] expectedMatrix = {
      0x00, (byte) 0xFF, 0x00, (byte) 0xFF,
      (byte) 0xFF, 0x00, (byte) 0xFF, 0x00
    };
    assertArrayEquals(expectedMatrix, matrix);

    RGBLuminanceSource cropped1 = (RGBLuminanceSource) source.crop(0, 0, 2, 2);
    byte[] croppedMatrix1 = cropped1.getMatrix();
    byte[] expectedCroppedMatrix1 = {
      0x00, (byte) 0xFF,
      (byte) 0xFF, 0x00
    };
    assertArrayEquals(expectedCroppedMatrix1, croppedMatrix1);

    RGBLuminanceSource cropped2 = (RGBLuminanceSource) source.crop(2, 0, 2, 2);
    byte[] croppedMatrix2 = cropped2.getMatrix();
    byte[] expectedCroppedMatrix2 = {
      0x00, (byte) 0xFF,
      (byte) 0xFF, 0x00
    };
    assertArrayEquals(expectedCroppedMatrix2, croppedMatrix2);
  }

  @Test
  void testGetRow() {
    int[] pixels = {
      0xFF000000, 0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF,
      0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF, 0xFF000000
    };
    RGBLuminanceSource source = new RGBLuminanceSource(4, 2, pixels);
    byte[] row = new byte[4];
    source.getRow(1, row);
    byte[] expectedRow = {(byte) 0xFF, 0x00, (byte) 0xFF, 0x00};
    assertArrayEquals(expectedRow, row);
  }

  @Test
  void testCrop() {
    int[] pixels = {0xFF000000}; // Black pixel
    RGBLuminanceSource source = new RGBLuminanceSource(1, 1, pixels);
    RGBLuminanceSource cropped = (RGBLuminanceSource) source.crop(0, 0, 1, 1);
    assertEquals(1, cropped.getWidth());
    assertEquals(1, cropped.getHeight());
    assertEquals(0x00, cropped.getMatrix()[0]); // Corrected expected value
  }

  @Test
  void testToString() {
    int[] pixels = {
      0xFF000000, 0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF,
      0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF, 0xFF000000
    };
    RGBLuminanceSource source = new RGBLuminanceSource(4, 2, pixels);
    String expectedString = "# # \n # #\n"; // Adjusted expected format
    assertEquals(expectedString, source.toString());
  }
}
// 1-4 4/4
