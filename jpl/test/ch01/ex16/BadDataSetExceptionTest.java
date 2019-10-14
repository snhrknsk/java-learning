package ch01.ex16;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BadDataSetExceptionTest {

	@Test
	public void testThrow() {
		try {
			throw new BadDataSetException("test", new IOException());
		}catch (BadDataSetException e) {
			assertEquals("test", e.getBadDataName());
		}
	}

}
