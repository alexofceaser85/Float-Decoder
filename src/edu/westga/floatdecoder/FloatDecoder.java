package edu.westga.floatdecoder;

/**
 * Decodes a 32-bit int as a 32-bit floating point value, 
 * bit-for-bit.
 * 
 * @author Alex DeCesare
 * @version 07-September-2021
 */
public class FloatDecoder {

	
	/**
	 * Indicates if the given 32-bit value represents a
	 * positive floating-point value.
	 * 
	 * @param value a 32-bit value
	 * @return true if value represents a positive floating point number, false otherwise
	 */
	public static boolean isPositive(int value) {
		int normalizedValue = value & 0b10000000000000000000000000000000;
		
		if (normalizedValue == 0b10000000000000000000000000000000) {
			return false;
		}
		return true;
	}

	/**
	 * Decodes the floating-point exponent portion of the 32-bit value.
	 * 
	 * @param value a 32-bit value
	 * @return the properly-biased exponent
	 */
	public static int decodeExponent(int value) {
		int normalizedValue = value & 0b01111111100000000000000000000000;
		int shiftedValue = normalizedValue >>> 23;
		int unbiasedValue = shiftedValue - 127;
		return unbiasedValue;
	}
	
	/**
	 * Decodes the significant-digits portion of the 32-bit value.
	 * 
	 * @param value a 32-bit value
	 * @return the significant-digits of the value, as a float. Note this float represents ONLY
	 * the significant digits portion, i.e., it will be "x2^0"
	 */
	public static float decodeSignificantDigits(int value) {
		throw new UnsupportedOperationException("not implemented");
	}
	
	/**
	 * Creates a float from the given 32-bit integer value.
	 * 
	 * @param value a 32-bit value
	 * @return
	 */
	public static float decodeAsFloat(int value) {
		throw new UnsupportedOperationException("not implemented");
	}
	
	/**
	 * Determines if the 32-bit value is one of the valid zero representations.
	 * 
	 * @param value a 32-bit value
	 * @return true if positive or negative zero, false otherwise
	 */
	public static boolean isZero(int value) {
		if (value == 0b10000000000000000000000000000000 || value == 0b00000000000000000000000000000000) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines if the 32-bit value represents Not-A-Number.
	 * 
	 * @param value a 32-bit value
	 * @return true if NaN, false otherwise
	 */
	public static boolean isNaN(int value) {
		return false;
	}
	
	/**
	 * Determines if the 32-bit value is one of the infinities.
	 * 
	 * @param value a 32-bit value
	 * @return true if +Infinity or -Infinity; false otherwise
	 */
	public static boolean isAnInfinity(int value) {
		if (value == 0b1111111100000000000000000000000 || value == 0b11111111100000000000000000000000) {
			return true;
		}
		
		return false;
	}
}
