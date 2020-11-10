package ch03.ex23;

import java.util.function.Function;

public class Pair<T> {
	protected T v1;
	protected T v2;

	public Pair(T v1, T v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	public <U> Pair<U> map(Function<T, U> f) {
		return new Pair<>(f.apply(v1), f.apply(v2));
	}
}
