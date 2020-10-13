package ch02.ex02;

import java.util.Arrays;

public class WordFilter {

	public static void main(String[] args) {
		String[] words = {"1", "12", "123", "1234", "12345", "123456", "1234567", "12345678"/*ここまで*/, "123456789", "1234567890", "12345678901"};
		Arrays.stream(words).filter(s->{
			System.out.println("Filter:\t" + s);
			return s.length() > 3;
		}).limit(5).forEach(s->{});
	}
}
