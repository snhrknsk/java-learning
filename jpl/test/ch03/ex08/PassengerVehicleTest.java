package ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class PassengerVehicleTest {

	@Test
	public void testInitialize() {
		PassengerVehicle passengerVehicle = new PassengerVehicle("User1", 4);
		passengerVehicle.setUsingSeatNum(2);

		assertEquals(passengerVehicle.getOwner(), "User1");
		assertEquals(passengerVehicle.getSeatNum(), 4);
		assertEquals(passengerVehicle.getUsingSeatNum(), 2);
		assertEquals(passengerVehicle.getId(), 1);

		passengerVehicle = new PassengerVehicle(4);
		assertEquals(passengerVehicle.getOwner(), "");
		assertEquals(passengerVehicle.getSeatNum(), 4);
		assertEquals(passengerVehicle.getId(), 2);

		PassengerVehicle target = new PassengerVehicle("User2", 5);
		PassengerVehicle clone = target.clone();
		assertThat(clone.getId(), is(not(target.getId())));
		assertEquals(clone.getOwner(), target.getOwner());
		assertEquals(clone.getSeatNum(), target.getSeatNum());
	}

}
