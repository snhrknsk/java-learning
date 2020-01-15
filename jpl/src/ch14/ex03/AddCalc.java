package ch14.ex03;

public class AddCalc {

	private long value = 0;

	public synchronized void add() {
		long preValue = value;
		value ++;
		System.out.println( Thread.currentThread().getName() + " : " + preValue + " + " + 1 + " = " + value);
	}

	public static void main(String[] args) {

		AddCalc calc = new AddCalc();

		Runnable run = new Runnable() {

			@Override
			public void run() {
				while(true) {
					calc.add();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
					}
				}
			}
		};

		for (int i = 0; i < 5; i++) {
			new Thread(run).start();
		}
	}

}
