package chap2.excercise2_1;

public class Vehicle {
	private double speed = 0;
	private double direction = 0;
	private String owner = "";

	public Vehicle() {
	}

	public Vehicle(String owner) {
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

}
