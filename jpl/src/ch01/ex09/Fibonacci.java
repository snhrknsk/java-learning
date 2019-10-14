package ch01.ex09;

public class Fibonacci {

	static final int MAX_INDEX = 9;
	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初の方の要素を表示する
	 */
	public static void main(String[] args) {

		int lo = 1;
		int hi = 1;
		int[] result = new int[MAX_INDEX];

		result[0] = lo;
		for (int i = 1; i < MAX_INDEX ; i++) {
			result[i] = hi;
			hi = lo + hi;
			lo = hi -lo;
		}

		for (int i : result) {
			System.out.println(i);
		}
	}
}