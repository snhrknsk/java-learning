package ch08.ex05;

import java.util.List;

public class WordCounter {

	public static long wordCount(List<String> words) {
		words.removeIf(w -> w.length() <= 12);
		return words.size();
	}

	public static long wordCountCh02(List<String> words) {
		
		return words.stream().filter((w) -> w.length() > 12).count();
	}

}
