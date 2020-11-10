package ch03.ex17;

import java.util.function.Consumer;

public class DoParallelAsync {

	public static <T> void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					first.run();
				} catch (Exception e) {
					handler.accept(e);
				}
			}
		};
		t.start();

		Thread t2 = new Thread() {
			public void run() {
				try {
					second.run();
				} catch (Exception e) {
					handler.accept(e);
				}
			}
		};
		t2.start();
	}
}
