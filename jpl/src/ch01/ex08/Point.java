package ch01.ex08;

public class Point {

	public double x, y;
	public static Point origin = new Point();

	public void clear() {
		x = 0.0;
		y = 0.0;
	}

	public double distance(Point that) {
		double xdiff = x - that.x;
		double ydiff = y - that.y;
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff );
	}

	public void setPoint(Point that) {
		this.x = that.x;
		this.y = that.y;
	}
}
