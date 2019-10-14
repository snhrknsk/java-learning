package ch02.ex17;

public class Vehicle {

	private static long nextSerialNo = 1;
	private final long id;
	private double speed = 0;
	private double direction = 0;
	private String owner = "";

	public static final String TURN_LEFT = "left";
	public static final String TURN_RIGHT = "right";

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

	@Override
	public String toString() {
		return "ID:" + id + ", Owner:" + owner + ", Speed:" + speed + "km/h, Direction:" + direction +"°";
	}

	public static long getLastSerialNumber() {
		return nextSerialNo - 1;
	}

}
