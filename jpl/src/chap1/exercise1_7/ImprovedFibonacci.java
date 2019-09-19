package chap1.exercise1_7;

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

		System.out.println("9: " + lo);
		for (int i = MAX_INDEX; i > 1 ; i--) {
			if (i % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println( i -1 + ": " + hi + mark);
			hi = lo + hi;
			lo = hi -lo;
		}
	}

}
