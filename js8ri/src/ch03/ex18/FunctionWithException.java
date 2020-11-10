package ch03.ex18;

import java.util.function.Function;

public class FunctionWithException {

	public static <T, U> Function<T, U> unchecked(FuncWithExceptions<T, U> f) {

	return (u) -> {
		try {
			return f.apply(u);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	};
}

	@FunctionalInterface
	public interface FuncWithExceptions<T, U>{
		U apply(T t) throws Exception;
	}
}