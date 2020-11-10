package ch03.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class FutureMap {

	public static <T, U> Future<U> map(Future<T> future, Function<T, U> f) {

		Future<U> result = new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {

				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return f.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
				return f.apply(future.get(timeout, unit));
			}
		};
		return result;
	}
}
