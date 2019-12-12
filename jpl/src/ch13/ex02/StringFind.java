package ch13.ex02;

public class StringFind {


	public static int findCount(String target, String searchWord) {
		if (searchWord.length() == 0) {
			return 0;
		}
		return (target.length() - target.replaceAll(searchWord, "").length()) / searchWord.length();
	}

}
