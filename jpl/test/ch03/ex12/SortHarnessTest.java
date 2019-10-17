package ch03.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class SortHarnessTest {

	SortHarness target;

	Comparator<Object> comparator = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			return o1.toString().compareTo(o2.toString());
		}
	};

	@Before
	public void setUp() throws Exception {
		target = new SortHarness() {
			@Override
			protected void doSort() {
			}
		};
	}

	@Test
	public void testProbe() {
		target.sort(new Object[] {  "abc", "bcd", "cde" }, comparator);
		assertThat(target.getDataLength(), is(3));
		assertThat(target.probe(0), is((Object) "abc"));
	}

	@Test
	public void testCompare() {
		target.sort(new Object[] { "abc", "bcd", "abc" }, comparator);
		assertThat(target.compare(0, 1), is(-1));
		assertThat(target.compare(0, 2), is(0));
		assertThat(target.compare(1, 0), is(1));
	}

	@Test
	public void testSwap() {
		Object[] testData = new Object[] { "abc", "bcd", "cde" };
		target.sort(testData, comparator);
		target.swap(0, 1);
		assertThat(testData[0], is((Object) "bcd"));
		assertThat(testData[1], is((Object) "abc"));
	}

}
