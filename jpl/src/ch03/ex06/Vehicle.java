package ch03.ex06;

import java.util.ArrayList;
import java.util.List;


public class Vehicle {
	public static long nextSerialNo = 1;
	private final long id;
	private double speed = 0;
	private double direction = 0;
	private String owner = "";
	private final EnergySource energy;

	public static final String TURN_LEFT = "left";
	public static final String TURN_RIGHT = "right";

	public Vehicle(EnergySource energy) {
		id = nextSerialNo;
		nextSerialNo++;
		this.energy = energy;
	}

	public Vehicle(String owner, EnergySource energy) {
		this(energy);
		this.owner = owner;
	}

	public double getSpeed() {
		return speed;
	}

	public void changeSpeed(double speed) {
		this.speed = speed;
	}

	public void stop() {
		speed = 0;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String newOwner) {
		owner = newOwner;
	}

	public long getId() {
		return id;
	}

	public void turn(double turn) {
		direction += turn;
	}

	public void turn(String turn) {
		switch (turn) {
		case TURN_LEFT:
			direction -= 90;
			break;
		case TURN_RIGHT:
			direction += 90;
			break;
		default:
			throw new IllegalArgumentException("must be TURN_LEFT or TURN_RIGHT");
		}
	}

	/**
	 * 稼働開始できるかどうか
	 * @return true:稼働可能 false:稼働不可
	 */
	public boolean start() {
		if (energy.empty()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ID:" + id + ", Owner:" + owner + ", Speed:" + speed + "km/h, Direction:" + direction +"°";
	}

	public static long getLastSerialNumber() {
		return nextSerialNo - 1;
	}

	public static void main(String[] args) {
		List<Vehicle> vehicleList = new ArrayList<>();
		for (String owner : args) {
			vehicleList.add(new Vehicle(owner, new GasTank(50)));
		}
		for (Vehicle vehicle : vehicleList) {
			System.out.println(vehicle.toString());
		}
	}
}
