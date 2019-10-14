package ch01.ex04;

public class Square {
	public static void main(String[] args) {
		System.out.println("平方表を表示");
		for (int i = 0; i < 10; i++) {
			System.out.println( i + " : " + Math.sqrt(i));
		}
	}
}
