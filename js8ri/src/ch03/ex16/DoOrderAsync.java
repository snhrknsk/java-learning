package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoOrderAsync {

	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable e) {
					try {
						second.accept(null, e);
					}catch (Throwable t) {
						// BiConsumerで処理できるのでhandler不要？
						handler.accept(e);
					}
				}
			}
		};
		t.start();
	}
}
