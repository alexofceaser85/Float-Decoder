package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestIsZero {

	private static final float TOL = 0.0001f;
	
	@Test
	void testWhenPositiveZero() {
		float x = Float.intBitsToFloat(0);
		assertTrue(FloatDecoder.isZero(Float.floatToRawIntBits(x)));
	}
	
	@Test
	void testWhenNegativeZero() {
		float x = Float.intBitsToFloat(0x80000000);
		assertTrue(FloatDecoder.isZero(Float.floatToRawIntBits(x)));
	}

	@Test
	void testWhenNotZero() {
		float x = Float.intBitsToFloat(0x88886666);
		assertFalse(FloatDecoder.isZero(Float.floatToRawIntBits(x)));
	}
}
