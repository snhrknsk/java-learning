package ch01.ex09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {

	public default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		this.stream().filter(filter).forEach(action);
	}

	public static void main() {
		// 指定した文字数の文字のみを表示する
		String[] names = {"Hoge", "Foo", "Bar", "Test"};
		Collection2<String> col = new ArrayList2<>();
		Collections.addAll(col, names);
		col.forEachIf(System.out::println, s -> s.length() == 3);
	}

	/**
	 * ArrayListを拡張かつ{@link Collection2}を実装して{@link Collection2#forEachIf(Consumer, Predicate)}を利用できるようにしたクラス
	 * @param <T>
	 */
	class ArrayList2<T> extends ArrayList<T> implements Collection2<T>{
	}

}
