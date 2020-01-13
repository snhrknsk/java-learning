package ch10.ex04;

public class Fibonacci {

	static final int MAX_INDEX = 9;
	/**
	 * ch1.ex9の問題
	 */
	public static void main(String[] args) {

		int lo = 1;
		int hi = 1;
		int[] result = new int[MAX_INDEX];

		result[0] = lo;
		int i = 1;
		while(i<MAX_INDEX) {
			result[i] = hi;
			hi = lo + hi;
			lo = hi -lo;
			i++;
		}

		i=0;
		while(i<result.length) {
			System.out.println(result[i]);
			i++;
		}
	}
}