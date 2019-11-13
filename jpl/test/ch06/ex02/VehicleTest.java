package ch06.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import ch06.ex02.Vehicle.TURN_DIRECTION;

public class VehicleTest {

	Vehicle vehicle ;

	@Test
	public void test() {
		double expected = 10;
		vehicle = new Vehicle();
		vehicle.setDirection(100);
		vehicle.turn(TURN_DIRECTION.TURN_LEFT);
		assertThat(vehicle.getDirection(), is(expected));
		vehicle.turn(TURN_DIRECTION.TURN_RIGHT);
		expected = 100;
		assertThat(vehicle.getDirection(), is(expected));
	}

}
