package ch03.ex20;import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Map {

	public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		List<U> result = new ArrayList<>();
		for (T elem: list) {
			result.add(f.apply(elem));
		}
		return result;
	}
}
