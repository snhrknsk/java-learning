package ch06.ex04;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Stream;

public class FindLongMaxMin {

	public static long findMaxValue(Stream<Long> stream) {
		LongAccumulator longAccumulator = new LongAccumulator(Long::max, 0);
		stream.parallel().forEach(l -> longAccumulator.accumulate(l));
		return longAccumulator.get();
	}

	public static long findMinValue(Stream<Long> stream) {
		LongAccumulator longAccumulator = new LongAccumulator(Long::min, 0);
		stream.parallel().forEach(l -> longAccumulator.accumulate(l));
		return longAccumulator.get();
	}

}
