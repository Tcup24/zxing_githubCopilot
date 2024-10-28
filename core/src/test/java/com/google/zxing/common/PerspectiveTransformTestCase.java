package com.google.zxing.common;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PerspectiveTransformTestCase {

  private static final float EPSILON = 1e-6f;

  @Test
  public void testSquareToQuadrilateral() {
    PerspectiveTransform transform = PerspectiveTransform.quadrilateralToQuadrilateral(
      0, 0, 1, 0, 1, 1, 0, 1,
      10, 20, 20, 20, 20, 30, 10, 30);

    assertPointEquals(transform, 0, 0, 10, 20);
    assertPointEquals(transform, 1, 0, 20, 20);
    assertPointEquals(transform, 1, 1, 20, 30);
    assertPointEquals(transform, 0, 1, 10, 30);
  }

  @Test
  public void testQuadrilateralToQuadrilateral() {
    PerspectiveTransform transform = PerspectiveTransform.quadrilateralToQuadrilateral(
      10, 20, 20, 20, 20, 30, 10, 30,
      30, 40, 40, 40, 40, 50, 30, 50);

    assertPointEquals(transform, 10, 20, 30, 40);
    assertPointEquals(transform, 20, 20, 40, 40);
    assertPointEquals(transform, 20, 30, 40, 50);
    assertPointEquals(transform, 10, 30, 30, 50);
  }

  private void assertPointEquals(PerspectiveTransform transform, float x, float y, float expectedX, float expectedY) {
    float[] points = {x, y};
    transform.transformPoints(points);
    assertEquals(expectedX, points[0], EPSILON);
    assertEquals(expectedY, points[1], EPSILON);
  }
}// 1 2/2
