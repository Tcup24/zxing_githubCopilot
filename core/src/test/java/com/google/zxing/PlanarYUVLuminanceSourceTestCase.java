package com.google.zxing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanarYUVLuminanceSourceTestCase {

  private byte[] yuvData;
  private int width;
  private int height;

  @BeforeEach
  public void setUp() {
    width = 4;
    height = 4;
    yuvData = new byte[]{
      0, 1, 2, 3,
      4, 5, 6, 7,
      8, 9, 10, 11,
      12, 13, 14, 15
    };
  }

  @Test
  public void testNoCrop() {
    PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(yuvData, width, height, 0, 0, width, height, false);
    byte[] matrix = source.getMatrix();
    assertArrayEquals(yuvData, matrix);
  }

  @Test
  public void testCrop() {
    int cropLeft = 1;
    int cropTop = 1;
    int cropWidth = 2;
    int cropHeight = 2;
    PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(yuvData, width, height, cropLeft, cropTop, cropWidth, cropHeight, false);
    byte[] expectedCroppedData = new byte[]{
      5, 6,
      9, 10
    };
    byte[] croppedMatrix = source.getMatrix();
    assertArrayEquals(expectedCroppedData, croppedMatrix);
  }

} //2-4 2/3
