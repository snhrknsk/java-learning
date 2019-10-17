package ch03.ex11;

import org.junit.Before;
import org.junit.Test;

public class FixSortDoubleTest {
	SortDouble target;


	@Before
	public void setUp() throws Exception {
		target = new IllegalSort();
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalSort() {
		double[] testData = { 1, 0.3, 3.17 };
		FixSortDouble bsort = new FixSortDouble() {

			private boolean secondSort = false;

			@Override
			protected void doSort() {
				if (secondSort) {
					return;
				}
				for (int i = 0; i < getDataLength(); i++) {
					for (int j = i + 1; j < getDataLength(); j++) {
						if (compare(i, j) > 0) {
							swap(i, j);
						}
					}
				}
				secondSort = true;
				//もう一度sortを呼び出しメトリクス初期化
				sort(null);
			}

		};
		SortMetrics metrics = bsort.sort(testData);
	}

}
