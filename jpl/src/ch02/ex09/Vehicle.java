package ch02.ex09;

public class Vehicle {
	private static long nextSerialNo = 1;
	private final long id;
	private double speed = 0;
	private double direction = 0;
	private String owner = "";

	public Vehicle() {
		id = nextSerialNo;
		nextSerialNo++;
	}

	public Vehicle(String owner) {
		this();
		this.owner = owner;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public static long getLastSerialNumber() {
		return nextSerialNo - 1;
	}
}
