package ch04.ex02;

import java.util.Comparator;

import ch03.ex11.SortMetrics;

public interface ISortHarness {

	SortMetrics sort(Object[] data, Comparator<Object> comparator);

	public SortMetrics getMetrics();

	int getDataLength();

	Object probe(int i);

	int compare(int i, int j);

	void swap(int i, int j);

	abstract void doSort();
}
