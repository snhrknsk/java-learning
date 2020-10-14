package ch02.ex05;

import java.util.stream.Stream;

public class RamdamGenerator {

	/**
	 * x<sub>n+1</sub>=(ax<sub>n</sub>+c)%mで生成される線形合同生成機による乱数の無限ストリームを生成します
	 *
	 * @param a
	 * @param c
	 * @param m
	 * @param seed
	 * @return 乱数の無限ストリーム
	 */
	public static Stream<Long> genRandomStream(long a, long c, long m, long seed) {

		return Stream.iterate(seed, x -> ((a * x + c) % m));
	}

	public static void main(String[] args) {

		genRandomStream(25214903917L, 11, (long)Math.pow(2, 48), System.currentTimeMillis()).limit(120).forEach(System.out::println);
	}
}
