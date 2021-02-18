package ch08.ex06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

class PointComparatorTest {

	@Test
	void testPoint() {
		Point2D point0 = new Point2D(0,  0);
		Point2D point1 = new Point2D(0, 0);
		Point2D point2 =  new Point2D(10, 0);
		Point2D point3 =  new Point2D(0, 10);

		Comparator<Point2D> comparator = PointComparator.getPoint2DComparator();
		assertEquals(0, comparator.compare(point0, point1));
		assertEquals(-1, comparator.compare(point1, point2));
		assertEquals(1, comparator.compare(point3, point1));
	}

	void testRect() {
		Rectangle2D r0 = new Rectangle2D(0, 0, 100, 200);
		Rectangle2D r1 = new Rectangle2D(0, 0, 100, 200);
		Rectangle2D r2 = new Rectangle2D(10, 0, 100, 200);
		Rectangle2D r3 = new Rectangle2D(0, 10, 100, 200);
		Rectangle2D r4 = new Rectangle2D(0, 0, 200, 200);
		Rectangle2D r5 = new Rectangle2D(0, 0, 100, 300);

		Comparator<Rectangle2D> comparator = PointComparator.getRectangle2DComparrator();
		assertEquals(0, comparator.compare(r0, r1));
		assertEquals(-1, comparator.compare(r1, r2));
		assertEquals(1, comparator.compare(r3, r1));
		assertEquals(1, comparator.compare(r1, r4));
		assertEquals(1, comparator.compare(r1, r5));
	}
}
