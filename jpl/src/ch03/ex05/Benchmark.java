package ch03.ex05;

public abstract class Benchmark {
	abstract void benchMark();

	public final long repeat(int count) {
		long start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			benchMark();
		}
		return (System.nanoTime() - start);
	}
}
