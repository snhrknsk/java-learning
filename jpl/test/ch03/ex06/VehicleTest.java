package ch03.ex06;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;


public class VehicleTest {

	Vehicle vehicle ;
	EnergySource energy;

	@Test
	public void testParam() {
		vehicle = new Vehicle(energy);
		vehicle.setOwner("user");
		vehicle.changeSpeed(100);
		vehicle.setDirection(30);
		assertEquals("user", vehicle.getOwner());
		assertEquals(100,  vehicle.getSpeed(), 0);
		assertEquals(30,  vehicle.getDirection(), 0);
	}

	@Test
	public void testSerialNo() {

		vehicle = new Vehicle(energy);
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
		vehicle = new Vehicle(energy);
		vehicle = new Vehicle(energy);
		assertEquals(2, vehicle.getId());
	}

	@Test
	public void testLastSerialNo() {

		vehicle = new Vehicle(energy);
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
		vehicle = new Vehicle(energy);
		vehicle = new Vehicle(energy);
		assertEquals(2, Vehicle.getLastSerialNumber());
	}

	@Test
	public void testToString() {
		String expected = "ID:1, Owner:user1, Speed:50.0km/h, Direction:10.0°";
		vehicle = new Vehicle(energy);
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
		vehicle = new Vehicle("user1",energy);
		vehicle.changeSpeed(50);
		vehicle.setDirection(10);
		assertEquals(expected, vehicle.toString());
	}

	@Test
	public void testStop() {
		vehicle = new Vehicle(energy);
		vehicle.changeSpeed(100);
		vehicle.stop();
		assertEquals(0, vehicle.getSpeed(), 0);
	}

	@Test
	public void testTurn() {
		vehicle = new Vehicle(energy);
		vehicle.setDirection(20);
		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(110, vehicle.getDirection(), 0);
		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(20, vehicle.getDirection(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailTurn() {
		vehicle = new Vehicle(energy);
		vehicle.turn("90");
	}

	@Test
	public void testStart() {
		GasTank energy = new GasTank(5);
		vehicle = new Vehicle(energy);
		assertEquals(false, vehicle.start());
		energy.addGas(3);
		assertEquals(true, vehicle.start());
	}



}
