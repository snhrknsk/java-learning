package ch22.ex14;

import java.util.StringTokenizer;

public class FloatReader {

	public static double sum (String source) {
		double sum = 0;
		StringTokenizer in = new StringTokenizer(source, " ");
		while(in.hasMoreTokens()) {
			String val = in.nextToken();
			sum += Double.valueOf(val);
		}
		return sum;
	}
}
