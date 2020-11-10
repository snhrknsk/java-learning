package ch03.ex18;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class FunctionWithExceptionTest {

	@Test
	void test() {
		Function<Integer, Integer> func = FunctionWithException.unchecked((in)->{return in.compareTo(5);});
		int actual = func.apply(5);
		assertEquals(0, actual);
	}

	@Test
	void testException() {
		Integer error = null;
		assertThrows(RuntimeException.class, ()->{
			Function<Integer, Integer> func = FunctionWithException.unchecked((in)->{return error.intValue();});
			func.apply(5);
		});
	}

}
