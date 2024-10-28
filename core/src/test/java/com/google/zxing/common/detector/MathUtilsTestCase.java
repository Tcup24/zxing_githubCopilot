package com.google.zxing.common.detector;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathUtilsTestCase {

  @Test
  public void testRound() {
    assertEquals(2, MathUtils.round(1.5f));
    assertEquals(-2, MathUtils.round(-1.5f));
    assertEquals(0, MathUtils.round(0.0f));
    assertEquals(Integer.MAX_VALUE, MathUtils.round(Float.POSITIVE_INFINITY));
    assertEquals(Integer.MIN_VALUE, MathUtils.round(Float.NEGATIVE_INFINITY));
    assertTrue(Float.isNaN(Float.NaN)); // Check if NaN is NaN
  }

  @Test
  public void testDistance() {
    assertEquals(5.0, MathUtils.distance(0, 0, 3, 4), 0.0001);
    assertEquals(0.0, MathUtils.distance(1, 1, 1, 1), 0.0001);
    assertEquals(5.0, MathUtils.distance(0.0f, 0.0f, 3.0f, 4.0f), 0.0001);
    assertEquals(0.0, MathUtils.distance(1.0f, 1.0f, 1.0f, 1.0f), 0.0001);
  }

  @Test
  public void testSum() {
    assertEquals(0, MathUtils.sum(new int[]{}));
    assertEquals(5, MathUtils.sum(new int[]{5}));
    assertEquals(15, MathUtils.sum(new int[]{1, 2, 3, 4, 5}));
    assertEquals(0, MathUtils.sum(new int[]{1, -1, 2, -2}));
  }
}
//1-2 3/3
