package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestIsNaN {

	@Test
	void testShouldBeNaN() {
		int bits = Float.floatToRawIntBits(Float.NaN);
		assertTrue(FloatDecoder.isNaN(bits));
	}
	
	@Test
	void testShouldNotBeNaN() {
		int bits = Float.floatToRawIntBits(3.14f);
		assertFalse(FloatDecoder.isNaN(bits));
	}

}
