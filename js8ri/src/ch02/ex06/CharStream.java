package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharStream {

	public static Stream<Character> getCharStream(String s) {
		return IntStream.range(0, s.length() - 1).mapToObj(s::charAt);
	}
}
