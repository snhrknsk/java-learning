package ch14.ex04;


public class AddCalc {

	private static long value = 0;

	public synchronized static void add() {
		long preValue = value;
		value ++;
		System.out.println( Thread.currentThread().getName() + " : " + preValue + " + " + 1 + " = " + value);
	}

	public static void main(String[] args) {

		Runnable run = new Runnable() {

			@Override
			public void run() {
				while(true) {
					AddCalc.add();
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
