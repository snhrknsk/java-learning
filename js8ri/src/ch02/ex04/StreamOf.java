package ch02.ex04;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamOf {

	public static void main(String[] args) {
		int[] values = {1, 4, 9, 16};
		// [I@...として出力される(型がint[]として解釈される)
		Stream.of(values).forEach(System.out::println);

		// Arrays.streamを利用する
		Arrays.stream(values).forEach(System.out::println);

	}
}
