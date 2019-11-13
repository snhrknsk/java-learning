package ch07.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PascalTest {

	@Test
	public void test() {

		int[][] expected = {
				{1},
				{1,1},
				{1,2,1},
				{1,3,3,1},
				{1,4,6,4,1}
		};
		int[][] result =Pascal.calcPascal(5);
		assertThat(result, is(expected));
	}

}
