package edu.westga.floatdecoder.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.floatdecoder.FloatDecoder;

class TestDecodeSignificantDigits {

	private static final float TOL = 0.0001f;
	@Test
	void testWhenAllZeroes() {
		assertEquals(1, FloatDecoder.decodeSignificantDigits(0));
	}
	
	@Test
	void testWhenAllOnes() {
		int bits = 0b00000000011111111111111111111111;
		int bitsWithExpoentZero = 0b00111111111111111111111111111111;
		assertEquals(Float.intBitsToFloat(bitsWithExpoentZero), FloatDecoder.decodeSignificantDigits(bits), TOL);
	}

	@Test
	void testWhenMsbOfSignificantDigitsIsOneRestZeroes() {
		int bits = 0b00000000010000000000000000000000;
		int bitsWithExpoentZero = 0b00111111110000000000000000000000;
		assertEquals(Float.intBitsToFloat(bitsWithExpoentZero), FloatDecoder.decodeSignificantDigits(bits), TOL);
	}
	
	@Test
	void testWhenLsbOfSignificantDigitsIsOneRestZeroes() {
		int bits = 0b00000000000000000000000000000001;
		int bitsWithExpoentZero = 0b00111111100000000000000000000001;
		assertEquals(Float.intBitsToFloat(bitsWithExpoentZero), FloatDecoder.decodeSignificantDigits(bits), TOL);
	}
	
	@Test
	void testWhenSomeBitsOneSomeZero() {
		int bits = 0b00000000010101000000000000000000;
		assertEquals(1 + 1./2. + 1./8. + 1./32., FloatDecoder.decodeSignificantDigits(bits), TOL);
	}
}
