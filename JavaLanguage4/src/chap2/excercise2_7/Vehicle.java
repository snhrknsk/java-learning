package chap2.excercise2_7;

public class Vehicle {
	public static long nextSerialNo = 1;
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

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ID:" + id + ", Owner:" + owner + ", Speed:" + speed + "km/h, Direction:" + direction +"°";
	}

	public static void main(String[] args) {
		Vehicle vehicle1 = new Vehicle("owner1");
		vehicle1.setDirection(0);
		vehicle1.setSpeed(10);
		Vehicle vehicle2 = new Vehicle("owner2");
		vehicle2.setDirection(180);
		vehicle2.setSpeed(20);
		Vehicle vehicle3 = new Vehicle("owner3");
		vehicle3.setDirection(45);
		vehicle3.setSpeed(40);
		System.out.println(vehicle1.toString());
		System.out.println(vehicle2.toString());
		System.out.println(vehicle3.toString());
	}
}
