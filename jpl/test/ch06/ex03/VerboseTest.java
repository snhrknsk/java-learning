package ch06.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VerboseTest {

	@Test
	public void test() {
		int expected = 0;
		assertThat(Verbose.SILENT.getVerbosity(), is(expected));
		expected = 1;
		assertThat(Verbose.TERSE.getVerbosity(), is(expected));
		expected = 2;
		assertThat(Verbose.NORMAL.getVerbosity(), is(expected));
		expected = 3;
		assertThat(Verbose.VERBOSE.getVerbosity(), is(expected));
	}
}
