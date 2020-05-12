package ch22.ex06;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class GaussianDistribution {


	//ASTAR_COUNT回で*1つ
	private static final int ASTAR_COUNT = 100;
	private static final int ROUND = 10;

	public static String gaussSimulation(int times) {
		Random r = new Random();
		Map<Double, Integer> resultMap = new TreeMap<>();
		for (int i = 0; i < times; i++) {
			//結果を丸める
			double result = ((double)Math.round(r.nextGaussian() * ROUND) / ROUND);
			if (resultMap.containsKey(result)) {
				resultMap.put(result, resultMap.get(result) + 1);
			} else {
				resultMap.put(result, 1);
			}
		}
		StringBuilder results = new StringBuilder();
		for (Entry<Double, Integer> element : resultMap.entrySet()) {
			results.append(String.format("%5.2f", ((double)element.getKey())));
			results.append(":");
			int value = element.getValue();
			for (int i = 0; i < (value / ASTAR_COUNT) + 1; i++) {
				results.append("*");
			}
			results.append(System.lineSeparator());
		}
		return results.toString();
	}

	public static void main(String[] args) {
		System.out.println(GaussianDistribution.gaussSimulation(100000));
	}

}
