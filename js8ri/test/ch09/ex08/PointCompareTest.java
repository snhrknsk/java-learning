package ch09.ex08;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class PointCompareTest {

	@Test
	void test() {
		Point p0 = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		Point p1 = new Point(10,  10);

		PointCompare pc0 = new PointCompare(p0);
		PointCompare pc1 = new PointCompare(p1);

		assertEquals(0, pc0.compareTo(p0));
		assertEquals(-1, pc0.compareTo(p1));
	}

}
