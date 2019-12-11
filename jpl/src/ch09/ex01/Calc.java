package ch09.ex01;

public class Calc {

	public static void main(String[] args) {

		double p = Double.POSITIVE_INFINITY;
		double n = Double.NEGATIVE_INFINITY;
		System.out.println(p + p);
		System.out.println(p + n);
		System.out.println(p - p);
		System.out.println(p - n);
		System.out.println(p * p);
		System.out.println(p * n);
		System.out.println(p / p);
		System.out.println(p / n);
		System.out.println(n / p);
	}

}
