package ch22.ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhichChars {
	private Set<Character> used = new HashSet<>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			used.add(c);
		}
	}

	@Override
	public String toString() {
		//sort
		List<Character> sortedIntList = new ArrayList<>(used);
		Collections.sort(sortedIntList);

		StringBuilder desc = new StringBuilder("[");
		for (Character character : sortedIntList) {
			desc.append(character);
		}
		return desc.append("]").toString();
	}

	public static void main(String[] args) {
		System.out.println(new WhichChars("Testing 1 2 3"));
	}
}
