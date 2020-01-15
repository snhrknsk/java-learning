package ch14.ex06;

public class MessageTimer {
	private long start;
	private int[] intervals;

	public MessageTimer(int... interval) {
		this.intervals = interval;
	}

	public synchronized void start() {
		start = System.currentTimeMillis();
		ShowMessage[] showMessages = new ShowMessage[intervals.length];
		for (int i = 0; i < showMessages.length; i++) {
			showMessages[i] = new ShowMessage(intervals[i]);
			showMessages[i].start();
		}

		for(;;) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println((System.currentTimeMillis() - start)/1000 + "秒経過");
			for (ShowMessage showMessage : showMessages) {
				showMessage.signalOn();;
			}
		}

	}

	public static class ShowMessage extends Thread{

		private int count = 0;
		private final int interval;

		public ShowMessage(int interval) {
			this.interval = interval;
		}

		@Override
		public void run() {
			for(;;) {
					waiting();
					count++;
				if (count == interval) {
					System.out.println(interval + "間隔のメッセージ");
					count = 0;
				}
			}
		}

		public synchronized void signalOn() {
			notifyAll();
		}

		private synchronized void waiting() {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		new MessageTimer(7,15).start();
	}
}
