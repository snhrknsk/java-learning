package chap1.exercise1_12;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;
	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		String[] resultString = new String[MAX_INDEX];

		resultString[0] = "1: " + lo;
		for (int i = 2; i <= MAX_INDEX ; i++) {
			if ( hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			resultString[i-1] = i + ": " + hi + mark;
			hi = lo + hi;
			lo = hi -lo;
		}

		for (String fibonacciResult : resultString) {
			System.out.println(fibonacciResult);
		}
	}
}
