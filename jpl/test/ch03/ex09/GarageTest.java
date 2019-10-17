package ch03.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GarageTest {

	Garage garage;

	@Test
	public void test() {
		garage = new Garage(5);
		Vehicle vehicle = new Vehicle();
		boolean addVehicle = garage.addVehicle(vehicle);
		assertThat(addVehicle, is(true));
		assertThat(garage.getVehicle(0), is(vehicle));

		garage.addVehicle(new Vehicle());
		garage.addVehicle(new Vehicle());
		garage.addVehicle(new Vehicle());
		garage.addVehicle(new Vehicle());
		addVehicle = garage.addVehicle(new Vehicle());
		assertThat(addVehicle, is(false));

		Garage garage2 = garage.clone();
		assertThat(garage, is(not(garage2)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		garage = new Garage(5);
		garage.getVehicle(10);
	}

}
