package ch10.ex05;

public class CharGenerator {

	public static String getCharsSequence(char start, char end) {
		StringBuilder charSequence = new StringBuilder();
		for (char c = start; c <= end; c++) {
			charSequence.append(c);
		}
		return charSequence.toString();
	}

	public static void main(String[] args) {
		String charsequence = getCharsSequence('A', 'z');
		System.out.println(charsequence);
	}
}
