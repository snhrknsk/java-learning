package ch03.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class IllegalSortTest {

	@Test
	public void testSort() {
		double[] testData = { 0.3, 3.14, 1 };
		double[] sortedData = { 0.3, 1, 3.14 };
		SortDouble bsort = new IllegalSort();
		SortMetrics metrics = bsort.sort(testData);
		assertThat(metrics.compareCnt, is(0L));
		assertThat(metrics.probeCnt, is(0L));
		assertThat(metrics.swapCnt, is(0L));
		assertThat(testData, is(sortedData));
	}

}
