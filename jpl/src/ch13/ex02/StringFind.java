package ch13.ex02;

public class StringFind {


	public int findCount(String target, String searchWord) {
		return (target.length() - target.replaceAll(searchWord, "").length()) / searchWord.length();
	}

	public static void main(String... str) {
		System.out.println(new StringFind().findCount("aabbabbttab", "ab"));
	}
}
