package ch13.ex06;

public class DecimalSeprator {

	public static String separate(String target, int digit, char separator) {

		StringBuilder stringBuilder = new StringBuilder(target);
		int length = target.length();
		while(length > digit) {
			stringBuilder.insert(length - digit, separator);
			length -= digit;
		}
		return stringBuilder.toString();
	}

	public static void main(String...strings) {
		System.out.println(DecimalSeprator.separate("1234567890", 8, '#'));
	}
}
