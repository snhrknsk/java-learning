package ch02.ex17;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class VehicleTest {

	Vehicle vehicle ;

	@Test
	public void testParam() {
		vehicle = new Vehicle();
		vehicle.setOwner("user");
		vehicle.changeSpeed(100);
		vehicle.setDirection(30);
		assertEquals("user", vehicle.getOwner());
		assertEquals(100,  vehicle.getSpeed(), 0);
		assertEquals(30,  vehicle.getDirection(), 0);
	}

	@Test
	public void testSerialNo() {

		vehicle = new Vehicle();
		try {
			Field field;
			field = vehicle.getClass().getDeclaredField("nextSerialNo");
			Field modifier = Field.class.getDeclaredField("modifiers");
			modifier.setAccessible(true);
			modifier.setInt(field, field.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
			field.set(null, 1);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
		vehicle = new Vehicle();
		vehicle = new Vehicle();
		assertEquals(2, vehicle.getId());
	}

	@Test
	public void testUsedSerialNo() {

		vehicle = new Vehicle();
		try {
			Field field;
			field = vehicle.getClass().getDeclaredField("nextSerialNo");
			Field modifier = Field.class.getDeclaredField("modifiers");
			modifier.setAccessible(true);
			modifier.setInt(field, field.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
			field.set(null, 1);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
		vehicle = new Vehicle();
		vehicle = new Vehicle();
		assertEquals(2, Vehicle.getLastSerialNumber());
	}

	@Test
	public void testToString() {
		String expected = "ID:1, Owner:user1, Speed:50.0km/h, Direction:10.0°";
		vehicle = new Vehicle();
		try {
			Field field;
			field = vehicle.getClass().getDeclaredField("nextSerialNo");
			Field modifier = Field.class.getDeclaredField("modifiers");
			modifier.setAccessible(true);
			modifier.setInt(field, field.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
			field.set(null, 1);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
		vehicle = new Vehicle("user1");
		vehicle.changeSpeed(50);
		vehicle.setDirection(10);
		assertEquals(expected, vehicle.toString());
	}

	@Test
	public void testStop() {
		vehicle = new Vehicle();
		vehicle.changeSpeed(100);
		vehicle.stop();
		assertEquals(0, vehicle.getSpeed(), 0);
	}

	@Test
	public void testTurn() {
		vehicle = new Vehicle();
		vehicle.setDirection(20);
		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(110, vehicle.getDirection(), 0);
		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(20, vehicle.getDirection(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailTurn() {
		vehicle = new Vehicle();
		vehicle.turn("90");
	}

}
