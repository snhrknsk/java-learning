package chap1.exercise1_10;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;
	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		FibonacciResult[] results = new FibonacciResult[MAX_INDEX];

		results[0] = new FibonacciResult(lo, false);
		for (int i = 1; i < MAX_INDEX ; i++) {
			results[i] = new FibonacciResult(hi, hi % 2 == 0);
			hi = lo + hi;
			lo = hi -lo;
		}

		for (int i = 0; i < results.length; i++) {
			System.out.println( i + 1 + ": " + results[i].toString());
		}
	}

}
