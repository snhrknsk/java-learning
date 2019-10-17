package ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GasTankTest {

	GasTank gasTank ;


	@Test
	public void testBattery() {
		gasTank = new GasTank(5);
		assertThat(gasTank.getGas(), is(0));
		assertThat(gasTank.empty(), is(true));
		gasTank.addGas(3);
		assertThat(gasTank.getGas(), is(3));
		gasTank.consumeGas(1);
		assertThat(gasTank.getGas(), is(2));
		gasTank.addGas(10);
		assertThat(gasTank.getGas(), is(5));
	}

}
