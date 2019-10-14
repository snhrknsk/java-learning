package ch01.ex10;

public class FibonacciResult {

	private final int result;
	private final boolean isEven;

	public FibonacciResult(int result, boolean isEven) {
		this.result = result;
		this.isEven = isEven;
	}

	@Override
	public String toString() {
		String mark = "";
		if (isEven) {
			mark = " *";
		}
		return result + mark;
	}
}
