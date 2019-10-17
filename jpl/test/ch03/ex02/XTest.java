package ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {

	@Test
	public void testX() {
		X x = new X();
		assertThat(x.fullMask, is(x.xMask));
	}

	@Test
	public void testMask() {
		X x = new X();
		int masked = x.mask(0xf0f0);
		assertThat(masked, is(0x00f0));
	}

	@Test
	public void testY() {
		Y y = new Y();
		assertThat(y.fullMask, is(y.xMask | y.yMask));
	}

}
