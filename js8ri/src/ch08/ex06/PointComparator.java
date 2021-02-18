package ch08.ex06;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class PointComparator {


	public static Comparator<Point2D> getPoint2DComparator(){
		Comparator<Point2D> compPoint2D = Comparator.comparingDouble((Point2D p) -> p.getX()).thenComparing(p -> p.getY());
		return compPoint2D;
	}

	public static Comparator<Rectangle2D> getRectangle2DComparrator() {
		Comparator<Rectangle2D> compRect2D = Comparator.comparingDouble((Rectangle2D r) -> r.getMinX()).thenComparing(r -> r.getMinY()).thenComparing(r -> r.getMaxX()).thenComparing(r -> r.getMaxY()).thenComparing(r -> r.getHeight()).thenComparing(r -> r.getWidth());
		return compRect2D;
	}
}
