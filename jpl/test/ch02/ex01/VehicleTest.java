package ch02.ex01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

	Vehicle vehicle ;

	@Before
	public void setUp() {
		vehicle = new Vehicle();
	}

	@Test
	public void testParam() {
		vehicle.setOwner("user");
		vehicle.setSpeed(100);
		vehicle.setDirection(30);
		assertEquals("user", vehicle.getOwner());
		assertEquals(100,  vehicle.getSpeed(), 0);
		assertEquals(30,  vehicle.getDirection(), 0);
	}

}
