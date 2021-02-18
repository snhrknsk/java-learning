package ch09.ex10;

import java.util.Objects;

public class LabelPoint implements Comparable<LabelPoint>{

	private String label;
	private int x;
	private int y;

	public LabelPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, x, y);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}else if (other instanceof LabelPoint) {
			LabelPoint lp = (LabelPoint)other;
			return Objects.equals(label, lp.label) && this.x == lp.x && this.y == lp.y;
		}
		return false;
	}

	@Override
	public int compareTo(LabelPoint other) {

		int strComp = label.compareTo(other.label);
		if (strComp != 0) {
			return strComp;
		}
		int xComp = Integer.compare(x, other.x);
		if (xComp != 0) {
			return xComp;
		}
		return Integer.compare(y, other.y);
	}

}
