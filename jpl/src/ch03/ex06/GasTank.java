package ch03.ex06;

public class GasTank extends EnergySource{

	private int gasAmount = 0;
	private final int MAX_AMOUNT;

	public GasTank(int maxAmount) {
		MAX_AMOUNT = maxAmount;
	}

	@Override
	public boolean empty() {
		if (gasAmount == 0) {
			return true;
		}
		return false;
	}

	public void addGas(int addAmount) {
		if (addAmount + gasAmount > MAX_AMOUNT) {
			gasAmount = MAX_AMOUNT;
			return;
		}
		gasAmount += addAmount;
	}

	public void consumeGas(int consumeAmount) {
		if (gasAmount - consumeAmount < 0) {
			gasAmount = 0;
			return;
		}
		gasAmount -= consumeAmount;
	}

	public int getGas() {
		return gasAmount;
	}
}
