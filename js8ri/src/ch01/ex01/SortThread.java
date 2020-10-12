package ch01.ex01;

import java.util.Arrays;

/**
 * 呼び出したスレッドで実行される。下記実行するとMainスレッドとsort内のスレッドIDが等しい。
 */
public class SortThread {

	public static void main(String[] args) {
		Integer[] intArray = {1,3,2,5,4};

		System.out.println("Main Thread ID : " + Thread.currentThread().getId());
		Arrays.sort(intArray, (v, v2)->{
			System.out.println("Sort Thread ID : " + Thread.currentThread().getId());
			return Integer.compare(v, v2);
		});
	}
}
