package ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class DelimitedString {

	public String[] delimitedString(String from, char start, char end) {
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
			return null;
		}
		return delimitedList.toArray(new String[delimitedList.size()]);
	}

	public static void main(String...strings ) {
		String[] s = new DelimitedString().delimitedString("abcaabcabbn", 'a', 'c');
		for (String string : s) {
			System.out.println(string);
		}
	}
}
