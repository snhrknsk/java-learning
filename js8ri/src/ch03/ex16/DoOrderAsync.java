package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoOrderAsync {

	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				T result = null;
				Throwable throwable = null;
				try {
					result = first.get();
				} catch (Throwable e) {
					throwable = e;
				}finally {
					try {
						second.accept(result, throwable);
					}catch (Throwable t) {
						handler.accept(t);
					}
				}
			}
		};
		t.start();
	}
}
