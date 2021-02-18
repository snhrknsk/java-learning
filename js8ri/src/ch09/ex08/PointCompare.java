package ch09.ex08;

import java.awt.Point;

public class PointCompare implements Comparable<Point> {

	private final int x;
	private final int y;

	public PointCompare(Point p) {
		x = p.x;
		y = p.y;
	}

	public int compareTo(Point other) {
		long lx = x;
		long ly = y;
		long otherX = other.x;
		long otherY = other.y;

		long diff = lx - otherX;
		if (diff == 0) {
			diff = ly - otherY;
			if (diff == 0 ) {
				return 0;
			} else if (diff < 0) {
				return -1;
			}
		} else if (diff < 0) {
			return -1;
		}
		return 1;
	}
}
