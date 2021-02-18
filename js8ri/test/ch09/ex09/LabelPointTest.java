package ch09.ex09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LabelPointTest {

	@Test
	void testSameObj() {

		LabelPoint lp = new LabelPoint("test", 1, 1);
		assertTrue(lp.equals(lp));
	}

	@Test
	void testSameValue() {

		LabelPoint lp0 = new LabelPoint("test", 1, 1);
		LabelPoint lp1 = new LabelPoint("test", 1, 1);
		assertTrue(lp0.equals(lp1));
		assertTrue(lp0.hashCode() == lp1.hashCode());
	}

	@Test
	void testDiff() {

		LabelPoint lp0 = new LabelPoint("test", 1, 1);
		LabelPoint lp1 = new LabelPoint("test1", 1, 1);
		LabelPoint lp2 = new LabelPoint("test1", 2, 1);
		LabelPoint lp3 = new LabelPoint("test1", 1, 2);

		assertFalse(lp0.equals(lp1));
		assertFalse(lp0.hashCode() == lp1.hashCode());

		assertFalse(lp1.equals(lp2));
		assertFalse(lp1.hashCode() == lp2.hashCode());

		assertFalse(lp2.equals(lp3));
		assertFalse(lp2.hashCode() == lp3.hashCode());
	}

}
