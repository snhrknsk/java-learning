package ch07.ex02;

public class NumberLiteral {

	static short shortValue;
	static int intValue;
	static long longValue;
	static float floatValue;
	static double doubleValue;

	public static void main(String[] args) {

		/*Short OK*/
		shortValue = 32767;
		/*Short NG : 32768(Short.MAX_VALUE=32767) 1L, 3.5f,3.5d, 1.8e+1*/

		/*int OK*/
		intValue = 2147483647;
		/*int NG : 2147483648, 1L 3.5f, 3.5d, 1.8e+1*/

		/*long OK*/
		longValue = 1L;
		longValue = 2147483647;
		longValue = 9223372036854775807L;
		/*long NG : 2147483648, 9223372036854775808L, 3.5f, 3.5d, 1.8e+1*/

		/*float OK*/
		floatValue = 2147483647;
		floatValue = 3.5f;
		floatValue = 9223372036854775807L;
		floatValue = 3.4e+38f;
		/*float NG 2147483648, 9223372036854775808L, 3.4e+39f, 3.5d, 1.8e+1*/

		/*double OK*/
		doubleValue = 2147483647;
		doubleValue = 3.5f;
		doubleValue = 3.5d;
		doubleValue = 1.7e+308;
		doubleValue = 1.8e+1f;
		doubleValue = 9223372036854775807L;
		/*double NG : 1.7e+309*/
	}

}
