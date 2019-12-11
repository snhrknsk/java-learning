package ch13.ex05;

public class DecimalSeparater {

	public static String separate(String target) {

		StringBuilder stringBuilder = new StringBuilder(target);
		int length = target.length();
		while(length > 3) {
			stringBuilder.insert(length - 3, ",");
			length -= 3;
		}
		return stringBuilder.toString();
	}

	public static void main(String...strings) {
		System.out.println(DecimalSeparater.separate("1234567890"));
	}
}
