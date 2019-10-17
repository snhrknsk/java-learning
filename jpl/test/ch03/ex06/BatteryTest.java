package ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BatteryTest {

	Battery battery ;


	@Test
	public void testBattery() {
		battery = new Battery(5);
		assertThat(battery.getBattery(), is(0));
		assertThat(battery.empty(), is(true));
		battery.addBattery(3);
		assertThat(battery.getBattery(), is(3));
		battery.consumeBattery(1);
		assertThat(battery.getBattery(), is(2));
		battery.addBattery(10);
		assertThat(battery.getBattery(), is(5));
	}

}
