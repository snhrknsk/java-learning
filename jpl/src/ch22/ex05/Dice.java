package ch22.ex05;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * どのメソッドでも変わらない
 */
public class Dice {

	public static String rollDice(int diceNum, int times) {
		if (diceNum <= 0 || times <= 0) {
			throw new IllegalStateException();
		}
		Map<Integer, Integer> results = new TreeMap<>();
		Random r = new Random();
		for (int i = 0; i < times; i++) {
			int result = 0;
			for (int j = 0; j < diceNum; j++) {
				result += r.nextInt(6) + 1;
			}
			if (results.containsKey(result)) {
				results.put(result, results.get(result) + 1);
			} else {
				results.put(result, 1);
			}
		}
		return results.toString();
	}

	public static void main(String[] args) {
		System.out.println(Dice.rollDice(3,1000));
	}
}
