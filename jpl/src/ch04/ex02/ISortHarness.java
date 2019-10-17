package ch04.ex02;

import java.util.Comparator;

import ch03.ex11.SortMetrics;

public interface ISortHarness {

	SortMetrics sort(Object[] data, Comparator<Object> comparator);
	SortMetrics getMetrics();
}
