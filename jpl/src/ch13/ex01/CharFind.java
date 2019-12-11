package ch13.ex01;

public class CharFind {

	public int findCount(String str, char c) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}

	public static void main(String... str) {
		System.out.println(new CharFind().findCount("aabba", 'a'));
	}
}
