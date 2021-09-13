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
		int maskedValue = value & 0b10000000000000000000000000000000;
		
		if (maskedValue == 0b10000000000000000000000000000000) {
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
		int maskedValue = value & 0b01111111100000000000000000000000;
		int shiftedValue = maskedValue >>> 23;
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
		int bitValue = value & 0b00000000011111111111111111111111;
		bitValue = bitValue | 0b00000000100000000000000000000000;
		
		float result = 0;
		
		int bitIndex = 23;
		for (int i = 0; i < 23; i++) {
			if (bitValue >= Math.pow(2, bitIndex)) {
				bitValue -= Math.pow(2, bitIndex);
				result += 1 / Math.pow(2, i);
			}
			
			bitIndex--;
		}

		return result;
	}
	
	/**
	 * Creates a float from the given 32-bit integer value.
	 * 
	 * @param value a 32-bit value
	 * @return the decoded float value
	 */
	public static float decodeAsFloat(int value) {
		if (isPositive(value)) {
			return decodeSignificantDigits(value) * (float) Math.pow(2, decodeExponent(value));
		} else {
			return -decodeSignificantDigits(value) * (float) Math.pow(2, decodeExponent(value));
		}
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
		if (decodeExponent(value) == 128 && decodeSignificantDigits(value) != 1) {
			return true;
		}
		
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
