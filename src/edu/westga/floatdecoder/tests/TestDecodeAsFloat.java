package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestDecodeAsFloat {

	private static final float TOL = 0.0001f;
	
	@Test
	void testShouldDecodePositiveNumber() {
		int bits = 0b01100000010000011100000100000000;
		assertEquals(Float.intBitsToFloat(bits), FloatDecoder.decodeAsFloat(bits), TOL);
	}
	
	@Test
	void testShouldDecodeNegativeNumber() {
		int bits = 0b11101100010000001000000000010000;
		assertEquals(Float.intBitsToFloat(bits), FloatDecoder.decodeAsFloat(bits), TOL);
	}

}
