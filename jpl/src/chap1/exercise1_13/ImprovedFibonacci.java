package chap1.exercise1_13;

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

		System.out.printf("1: %d%n", lo);
		for (int i = 2; i <= MAX_INDEX ; i++) {
			if ( hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.printf("%d: %d%s%n",i ,hi, mark);
			hi = lo + hi;
			lo = hi -lo;
		}
	}
}
