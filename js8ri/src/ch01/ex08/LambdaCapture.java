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

		/*
		//不可：iはfinalではないのでエラー, 添字をj = i;のようにloop内で定義すれば可能
		for (int i = 0; i < names.length; i++) {
			runners.add(()->System.out.println(names[i]));
		}
		*/
	}
}
