package ch03.ex09;

public class Garage implements Cloneable{

	private Vehicle[] garage;
	private int size = 0;

	public Garage(int maxSize) {
		garage = new Vehicle[maxSize];
		size = 0;
	}

	public boolean addVehicle(Vehicle vehicle) {
		if (garage.length > size) {
			garage[size++] = vehicle;
			return true;
		}
		return false;
	}

	public Vehicle getVehicle(int index) {
		if (index > size) {
			throw new IllegalArgumentException();
		}
		return garage[index];
	}

	@Override
	public Garage clone() {
		Garage copyGarage = new Garage(garage.length);
		for (int i = 0; i < garage.length; i++) {
			if (garage[i] != null) {
				copyGarage.addVehicle(garage[i].clone());
			}
		}
		return copyGarage;
	}

	public static void main(String...args) {
		Garage g1 = new Garage(10);
		g1.addVehicle(new Vehicle());
		Garage g2 = g1.clone();
		if (g2 == g1 || g2.garage == g1.garage) {
			System.out.println("clone failed");
		} else {
			System.out.println("clone completed.");
		}
	}
}
