package ch03.ex06;

public class Battery extends EnergySource {

	private int batteryAmount = 0;
	private final int MAX_AMOUNT;

	public Battery(int maxAmount) {
		MAX_AMOUNT = maxAmount;
	}

	@Override
	public	boolean empty() {
		if (batteryAmount == 0) {
			return true;
		}
		return false;
	}

	public void addBattery(int addAmount) {
			if (addAmount + batteryAmount > MAX_AMOUNT) {
				batteryAmount = MAX_AMOUNT;
				return;
			}
			batteryAmount += addAmount;
		}

	public void consumeBattery(int consumeAmount) {
		if (batteryAmount - consumeAmount < 0) {
			batteryAmount = 0;
			return;
		}
		batteryAmount -= consumeAmount;
	}

	public int getBattery() {
		return batteryAmount;
	}



}
