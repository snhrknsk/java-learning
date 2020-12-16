package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

public class SortPerformance {

	public static void main(String[] args) {

		int arraySize = 100;
		long sequentialTime = 0;
		long parallelTime = 0;

		do {
			arraySize *= 10;
			int[] intArray = new int[arraySize];
			int[] intArray2 = intArray.clone();
			Random random = new Random(System.currentTimeMillis());
			for (int i = 0; i < intArray.length; i++) {
				intArray[i] = random.nextInt();
			}
			long start = System.nanoTime();
			Arrays.sort(intArray);
			long end = System.nanoTime();
			sequentialTime = end - start;

			start = System.nanoTime();
			Arrays.parallelSort(intArray2);
			end = System.nanoTime();
			parallelTime = end - start;
			System.out.println(arraySize + " Arrays.sort:" + sequentialTime + "[ms] Arrays.parallelSort:" + parallelTime + "[ms]");
		} while (parallelTime >= sequentialTime);

	}
}
