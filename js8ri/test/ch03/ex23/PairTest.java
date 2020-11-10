package ch03.ex23;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {

	@Test
	void test() {
		Pair<String> str = new Pair<>("foo", "hogehoge");
		Pair<Integer> length = str.map((s) -> s.length());
		assertEquals(3, (int)length.v1);
		assertEquals(8, (int)length.v2);
	}

}
