package ch02.ex06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

class CharStreamTest {

	@Test
	void test() {
		char[] expected = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		AtomicInteger i = new AtomicInteger();
		CharStream.getCharStream("abcdefg").forEach((c)->{
			int index = i.getAndIncrement();
			System.out.println(c);
			assertEquals(expected[index], (char)c);
		});
	}

	@Test
	void testMultiByte() {
		char[] expected = {'あ', 'い', 'う', 'え', 'お', 'か', 'き'};
		AtomicInteger i = new AtomicInteger();
		CharStream.getCharStream("あいうえおかき").forEach((c)->{
			int index = i.getAndIncrement();
			System.out.println(c);
			assertEquals(expected[index], (char)c);
		});
	}

	@Test
	void testEmpty() {
		char[] expected = {};
		AtomicInteger i = new AtomicInteger();
		CharStream.getCharStream("").forEach((c)->{
			int index = i.getAndIncrement();
			System.out.println(c);
			assertEquals(expected[index], (char)c);
		});
	}

}
