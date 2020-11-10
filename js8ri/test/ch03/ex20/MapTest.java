package ch03.ex20;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void test() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Function<Integer, Integer> f = (i) -> i + 1;
		List<Integer> result = Map.map(list, f);
		for (int j = 0; j < list.size(); j++) {
			int expected = list.get(j) + 1;
			int actual = result.get(j);
			assertEquals(expected, actual);
		}
	}

}
