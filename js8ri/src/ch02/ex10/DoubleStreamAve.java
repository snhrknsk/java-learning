package ch02.ex10;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * reduceが終端処理のため同じStreamに対して二度目の終端処理はできない
 */
public class DoubleStreamAve {

	public static double average(Stream<Double> stream) {
		AtomicLong num = new AtomicLong();
		double result = stream.reduce(0.0, (val1, val2)->{
			num.incrementAndGet();
			return val1 + val2;
		});
		if (num.get() == 0) {
			return Double.NaN;
		}
		return result / num.get();
	}

	/*
	//reduce終端処理を一度行っているので二度目は終端操作を行うことだできずエラー
	public static double average2(Stream<Double> stream) {
		AtomicInteger index = new AtomicInteger();
		double result = stream.reduce(0.0, (val1, val2)->{
			index.incrementAndGet();
			return val1 + val2;
		});
		//ここでエラー
		long num = stream.count();
		return result / num;
	}
	*/

	public static void main(String[] args) {
		System.out.println(average(Stream.of(0.0, 1.2, 3.0)));
	}

}
