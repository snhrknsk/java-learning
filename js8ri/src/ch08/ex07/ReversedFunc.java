package ch08.ex07;

import java.util.Comparator;

public class ReversedFunc {

	public static Comparator<String> getNullsFirstComparator() {
		Comparator<String> nullFirstComp = Comparator.nullsLast(Comparator.reverseOrder());
		return nullFirstComp;
	}
}
