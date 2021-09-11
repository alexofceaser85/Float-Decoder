package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestIsAnInfinity {

	@Test
	void testForPositiveInfinity() {
		int bits = Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
		assertTrue(FloatDecoder.isAnInfinity(bits));
	}
	
	@Test
	void testForNegativeInfinity() {
		int bits = Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
		assertTrue(FloatDecoder.isAnInfinity(bits));
	}

	@Test
	void testForNotAnInfinity() {
		int bits = Float.floatToRawIntBits(3.14f);
		assertFalse(FloatDecoder.isAnInfinity(bits));
	}
}
