package ch02.ex03;

import java.util.stream.Stream;

public class StreamSpeed {

	public static long measureWordCountSpeed(Stream<String> s) {
		long start = System.nanoTime();
		s.filter((w)->w.length()>5).count();
		long end = System.nanoTime();
		return end - start;
	}

}
