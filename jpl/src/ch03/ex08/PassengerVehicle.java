package ch03.ex08;

import java.util.ArrayList;
import java.util.List;

public class PassengerVehicle extends Vehicle implements Cloneable {

	private final int seatNum;
	private int usingSeatNum = 0;

	PassengerVehicle(int seatNum) {
		super();
		this.seatNum = seatNum;
	}

	PassengerVehicle(String owner, int seatNum){
		super(owner);
		this.seatNum = seatNum;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public int getUsingSeatNum() {
		return usingSeatNum;
	}
	public void setUsingSeatNum(int usingSeatNum) {
		this.usingSeatNum = usingSeatNum;
	}

	@Override
	public String toString() {
		return "ID:" + getId() + ", Owner:" + getOwner() + ", Seat:" + getSeatNum() + ", UsingSeat:" + getUsingSeatNum();
	}

	public static void main(String[] args) {
		List<PassengerVehicle> vehicleList = new ArrayList<>();

		PassengerVehicle pVehicle = new PassengerVehicle("a", 5);
		pVehicle.setUsingSeatNum(2);
		vehicleList.add(pVehicle);
		pVehicle = new PassengerVehicle("b", 5);
		pVehicle.setUsingSeatNum(5);
		vehicleList.add(pVehicle);
		pVehicle = new PassengerVehicle("c", 4);
		pVehicle.setUsingSeatNum(1);
		vehicleList.add(pVehicle);

		for (Vehicle vehicle : vehicleList) {
			System.out.println(vehicle.toString());
		}
	}

	/**
	 * cloneによる単純なコピーだとIDが重複してしまう
	 */
	@Override
	public PassengerVehicle clone() {
		PassengerVehicle copyVehicle = new PassengerVehicle(getOwner(), seatNum);
		copyVehicle.setDirection(getDirection());
		copyVehicle.changeSpeed(getSpeed());
		return copyVehicle;

	}
}
