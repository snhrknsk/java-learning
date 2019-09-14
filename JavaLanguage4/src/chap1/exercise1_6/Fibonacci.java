package chap1.exercise1_6;

public class Fibonacci {

	static final String TITLE = "フィボナッチ数列を表示";
	/*値が50未満のフィボナッチ数列を表示する*/
	public static void main(String[] args) {
		System.out.println(TITLE);
		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}
