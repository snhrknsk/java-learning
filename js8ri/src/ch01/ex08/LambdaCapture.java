package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class LambdaCapture {

	public static void main(String[] args) {
		String[] names = {"Peter", "Paul", "Mary"};
		List<Runnable> runners = new ArrayList<>();

		for (String name : names) {
			runners.add(() -> System.out.println(name));
		}
		runners.stream().forEach(Runnable::run);

		// 変数iはfinalではないのでnames[i]とするとエラー
		// nameをaddの前に別変数へ代入しておく必要がある(もしくはj = iなどインデックスを別変数へ代入)
		List<Runnable> runners2 = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			runners2.add(()->System.out.println(name));
		}
		runners2.stream().forEach(Runnable::run);
	}
}
