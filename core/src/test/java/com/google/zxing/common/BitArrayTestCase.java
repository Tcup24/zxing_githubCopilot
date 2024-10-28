package com.google.zxing.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitArrayTestCase {

  @Test
  void testGetSet() {
    BitArray bitArray = new BitArray(10);
    for (int i = 0; i < 10; i++) {
      assertFalse(bitArray.get(i));
      bitArray.set(i);
      assertTrue(bitArray.get(i));
    }
  }

  @Test
  void testGetNextSet1() {
    BitArray bitArray = new BitArray(10);
    assertEquals(10, bitArray.getNextSet(0));
    bitArray = new BitArray(100);
    assertEquals(100, bitArray.getNextSet(0));
  }

  @Test
  void testGetNextSet2() {
    BitArray bitArray = new BitArray(10);
    bitArray.set(5);
    assertEquals(5, bitArray.getNextSet(0));
  }

  @Test
  void testGetNextSet3() {
    BitArray bitArray = new BitArray(20);
    bitArray.set(5);
    bitArray.set(15);
    assertEquals(5, bitArray.getNextSet(0));
    assertEquals(15, bitArray.getNextSet(6));
  }

  @Test
  void testGetNextSet4() {
    BitArray bitArray = new BitArray(20);
    bitArray.set(3);
    bitArray.set(7);
    bitArray.set(10);
    assertEquals(3, bitArray.getNextSet(0));
    assertEquals(7, bitArray.getNextSet(4));
    assertEquals(10, bitArray.getNextSet(8));
  }

  @Test
  void testGetNextSet5() {
    BitArray bitArray = new BitArray(50);
    for (int i = 0; i < 50; i += 5) {
      bitArray.set(i);
    }
    for (int i = 0; i < 50; i += 5) {
      assertEquals(i, bitArray.getNextSet(i));
    }
  }

  @Test
  void testSetBulk() {
    BitArray bitArray = new BitArray(32);
    bitArray.setBulk(0, 0xFFFF0000);
    for (int i = 0; i < 16; i++) {
      assertFalse(bitArray.get(i));
    }
    for (int i = 16; i < 32; i++) {
      assertTrue(bitArray.get(i));
    }
  }

  @Test
  void testSetRange() {
    BitArray bitArray = new BitArray(32);
    bitArray.setRange(8, 24);
    for (int i = 0; i < 8; i++) {
      assertFalse(bitArray.get(i));
    }
    for (int i = 8; i < 24; i++) {
      assertTrue(bitArray.get(i));
    }
    for (int i = 24; i < 32; i++) {
      assertFalse(bitArray.get(i));
    }
  }

  @Test
  void testClear() {
    BitArray bitArray = new BitArray(32);
    bitArray.setRange(0, 32);
    bitArray.clear();
    for (int i = 0; i < 32; i++) {
      assertFalse(bitArray.get(i));
    }
  }

  @Test
  void testFlip() {
    BitArray bitArray = new BitArray(10);
    bitArray.flip(5);
    assertTrue(bitArray.get(5));
    bitArray.flip(5);
    assertFalse(bitArray.get(5));
  }

  @Test
  void testGetArray() {
    BitArray bitArray = new BitArray(64);
    bitArray.set(0);
    bitArray.set(63);
    int[] array = bitArray.getBitArray();
    assertEquals(1, array[0]);
    assertEquals(1 << 31, array[1]);
  }

  @Test
  void testIsRange() {
    BitArray bitArray = new BitArray(64);
    bitArray.setRange(10, 20);
    assertTrue(bitArray.isRange(10, 20, true));
    assertFalse(bitArray.isRange(0, 10, true));
    assertFalse(bitArray.isRange(20, 30, true));
  }

  //3ter versuch
  @Test
  void reverseAlgorithmTest() {
    BitArray bitArray = new BitArray(64);
    bitArray.set(0);
    bitArray.set(63);
    bitArray.reverse();
    int[] reversed = bitArray.getBitArray();
    assertEquals(0x00000001, reversed[0]);
    assertEquals(0x80000000, reversed[1]);
  }

  @Test
  void testClone() {
    BitArray bitArray = new BitArray(10);
    bitArray.set(5);
    BitArray clone = bitArray.clone();
    assertEquals(bitArray, clone);
    clone.set(6);
    assertNotEquals(bitArray, clone);
  }

  @Test
  void testEquals() {
    BitArray bitArray1 = new BitArray(10);
    BitArray bitArray2 = new BitArray(10);
    assertEquals(bitArray1, bitArray2);
    bitArray1.set(5);
    assertNotEquals(bitArray1, bitArray2);
    bitArray2.set(5);
    assertEquals(bitArray1, bitArray2);
    assertEquals(bitArray1.hashCode(), bitArray2.hashCode());
  }
}
// 1-3 15/15
