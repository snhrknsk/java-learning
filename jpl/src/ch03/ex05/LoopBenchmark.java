package ch03.ex05;

public class LoopBenchmark extends Benchmark {

	private final int loopCount;

	public LoopBenchmark(int loop) {
		this.loopCount = loop;
	}

	@Override
	void benchMark() {
		for (int i = 0; i < loopCount; i++);
	}


}
