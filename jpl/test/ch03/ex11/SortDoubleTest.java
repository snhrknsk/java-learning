package ch03.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortDoubleTest {

	SortDouble target;

	@Before
	public void setUp() throws Exception {
		target = new SortDouble() {
			@Override
			protected void doSort() {
				//mock
			}
		};
	}

	@Test
	public void testGetDataLength() {
		target.sort(new double[] { 1, 2, 3 });
		assertThat(target.getDataLength(), is(3));
	}

	@Test
	public void testProbe() {
		target.sort(new double[] { 1, 2, 3 });
		assertThat(target.probe(1), is(2.0));
	}

	@Test
	public void testCompare() {
		target.sort(new double[] { 1, 2, 1 });
		assertThat(target.compare(0, 1), is(-1));
		assertThat(target.compare(0, 2), is(0));
		assertThat(target.compare(1, 0), is(1));
	}

	@Test
	public void testSwap() {
		double[] testData = new double[] { 1, 2, 3 };
		target.sort(testData);
		target.swap(0, 1);
		assertThat(testData[0], is(2));
		assertThat(testData[1], is(1));
	}

}
