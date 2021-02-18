package ch09.ex10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LabelPointTest {

	@Test
	void test() {
		LabelPoint lp0 = new LabelPoint("abc", 1, 1);
		LabelPoint lp1 = new LabelPoint("bcd", 1, 1);
		LabelPoint lp2 = new LabelPoint("bcd", 2, 1);
		LabelPoint lp3 = new LabelPoint("bcd", 2, 2);

		assertEquals(0, lp0.compareTo(lp0));

		assertEquals(-1, lp0.compareTo(lp1));
		assertEquals(1, lp1.compareTo(lp0));

		assertEquals(-1, lp1.compareTo(lp2));
		assertEquals(1, lp2.compareTo(lp1));

		assertEquals(-1, lp2.compareTo(lp3));
		assertEquals(1, lp3.compareTo(lp2));
	}

}
