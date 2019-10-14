package ch02.ex15;

public class Vehicle {
	private static long nextSerialNo = 1;
	private final long id;//変更不可能,一意に決めるものだから
	private double speed = 0;//変更可能,スピードは変わるから
	private double direction = 0;//変更可能,方向は変わるため
	private String owner = "";//変更可能,持ち主は変わるため

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

	@Override
	public String toString() {
		return "ID:" + id + ", Owner:" + owner + ", Speed:" + speed + "km/h, Direction:" + direction +"°";
	}

	public static long getLastSerialNumber() {
		return nextSerialNo - 1;
	}
}
