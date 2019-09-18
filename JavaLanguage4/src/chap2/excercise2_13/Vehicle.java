package chap2.excercise2_13;

public class Vehicle {
	public static long nextSerialNo = 1;
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
