package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestIsPositive {

	@Test
	void testZeroShouldBePositive() {
		assertTrue(FloatDecoder.isPositive(0));
	}
	
	@Test
	void testOneAtLsbRestZeroesShouldBePositive() {
		assertTrue(FloatDecoder.isPositive(0x00000001));
	}
	
	@Test
	void testAllOnesShouldBeNegative() {
		assertFalse(FloatDecoder.isPositive(0xffffffff));
	}

	@Test
	void testOneAtMsbRestZeroesShouldBeNegative() {
		assertFalse(FloatDecoder.isPositive(0x80000000));
	}
	
	@Test
	void testZeroAtMsbRestOnesShouldBePositive() {
		assertTrue(FloatDecoder.isPositive(0x7fffffff));
	}
}
