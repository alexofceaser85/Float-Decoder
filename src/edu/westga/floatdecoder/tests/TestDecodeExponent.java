package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestDecodeExponent {

	@Test
	void testShouldHaveMinimumExponent() {
		assertEquals(-127, FloatDecoder.decodeExponent(0));
	}
	
	@Test
	void testShouldHaveOneAboveMinimumExponent() {
		assertEquals(-126, FloatDecoder.decodeExponent(0b00000000100000000000000000000000));
	}
	
	@Test
	void testShouldHaveAllOnesExponent() {
		assertEquals(128, FloatDecoder.decodeExponent(0b01111111100000000000000000000000));
	}

	@Test
	void testShouldHaveMaximumExponent() {
		assertEquals(127, FloatDecoder.decodeExponent(0b01111111000000000000000000000000));
	}
	
	@Test
	void testShouldHaveOneBelowMaximumExponent() {
		assertEquals(126, FloatDecoder.decodeExponent(0b01111110100000000000000000000000));
	}
	
	@Test
	void testShouldDecodeExponent() {
		assertEquals(43, FloatDecoder.decodeExponent(0b01010101000000000000000000000000));
	}
}
