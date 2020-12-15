package ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class StringManager {

	private AtomicReference<String> longestString= new AtomicReference<>("");
	private final Stream<String> stream;

	public StringManager(Stream<String> s) {
		stream = s;
	}

	public String findLongestString() {
		stream.parallel().forEach(s -> {
			longestString.accumulateAndGet(s, (s1, s2) -> {
				return s1.length() > s2.length() ? s1 : s2;
			});
		});
		return longestString.get();
	}

}
