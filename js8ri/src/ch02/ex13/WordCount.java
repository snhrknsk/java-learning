package ch02.ex13;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

	private static int MAX_WORD_LENGTH = 5;

	/**
	 *
	 * @param words
	 * @return
	 */
	public static Map<Integer, Long> countShortWord(Stream<String> words) {
		Map<Integer, Long> result = words.parallel().filter((s)->s.length()<MAX_WORD_LENGTH).collect(Collectors.groupingBy(String::length, Collectors.counting()));
		for (int i = 0; i < result.size(); i++) {
			if (!result.containsKey(i)) {
				result.put(i, 0L);
			}
		}
		return result;
	}
}
