package ch02.ex12;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class WordCountPallarerl {

	private static int MAX_WORD_LENGTH = 5;

	/**
	 *  {@link MAX_WORD_LENGTH}より小さな文字数の単語の数を数える
	 * @param words
	 * @return
	 */
	public static AtomicInteger[] countShortWord(Stream<String> words) {
		AtomicInteger[] result = new AtomicInteger[MAX_WORD_LENGTH];
		for (int i = 0; i < result.length; i++) {
			result[i] = new AtomicInteger();
		}
		words.parallel().forEach((s)->{
			if (s.length() < MAX_WORD_LENGTH) {
				result[s.length()].getAndIncrement();
			}
		});
		return result;
	}

}
