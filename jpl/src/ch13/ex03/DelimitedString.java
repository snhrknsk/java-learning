package ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class DelimitedString {

	private static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static String[] delimitedString(String from, char start, char end) {
		int startPosition = 0;
		int endPosition = 0;
		List<String> delimitedList = new ArrayList<>();
		while ((startPosition = from.indexOf(start, endPosition)) != -1) {
			endPosition = from.indexOf(end, startPosition + 1);
			if (endPosition < 0) {
				delimitedList.add(from.substring(startPosition, from.length()));
				break;
			} else {
				delimitedList.add(from.substring(startPosition, endPosition + 1));
			}
		}

		if (delimitedList.isEmpty()) {
			return EMPTY_STRING_ARRAY;
		}
		return delimitedList.toArray(new String[delimitedList.size()]);
	}

	public static void main(String...strings ) {
		String[] s = DelimitedString.delimitedString("abcaabcabbn", 'a', 'c');
		for (String string : s) {
			System.out.println(string);
		}
	}
}
