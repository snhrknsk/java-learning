package ch08.ex01;

public class UnsignedCalc {

	public static int add(int v1, int v2) {

		return (int)(Integer.toUnsignedLong(v1) + Integer.toUnsignedLong(v2));
	}

	public static int sub(int v1, int v2) {

		return (int)(Integer.toUnsignedLong(v1) - Integer.toUnsignedLong(v2));
	}

	public static int div(int v1, int v2) {

		return (int)(Integer.toUnsignedLong(v1) / Integer.toUnsignedLong(v2));
	}

	public static int remain(int v1, int v2) {

		return (int)(Integer.toUnsignedLong(v1) % Integer.toUnsignedLong(v2));
	}

	public static int compare(int v1, int v2) {

		if (Integer.toUnsignedLong(v1) == Integer.toUnsignedLong(v2)) {
			return 0;
		} else if (Integer.toUnsignedLong(v1) < Integer.toUnsignedLong(v2)) {
			return 1;
		}
		return -1;
	}

	public static void main(String[] args) {

		int v1 = Integer.MAX_VALUE;
		int v2 = 10;
		System.out.println("加算");
		System.out.println("int\t\t: " + (v1 + v2));
		System.out.println("unsigned: " + add(v1, v2));

		System.out.println("減算");
		System.out.println("int\t\t: " + (v1 - v2));
		System.out.println("unsigned: " + sub(v1, v2));

		v1 = -1;
		System.out.println("除算");
		System.out.println("int\t\t: " + (v1 / v2));
		System.out.println("unsigned: " + div(v1, v2));
		System.out.println("divedeUnsigned: " + Integer.divideUnsigned(v1, v2));

		System.out.println("余り");
		System.out.println("int\t\t: " + (v1 % v2));
		System.out.println("unsigned: " + remain(v1, v2));
		System.out.println("remainderUnsigned: " + Integer.remainderUnsigned(v1, v2));

		System.out.println("比較");
		System.out.println("unsigned < : " + compare(1, 10));
		System.out.println("unsigned > : " + compare(10, 1));
		System.out.println("unsigned = : " + compare(1, 1));
	}
}
