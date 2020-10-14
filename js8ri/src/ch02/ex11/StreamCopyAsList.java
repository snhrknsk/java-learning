package ch02.ex11;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamCopyAsList {

	public static <T> void copyStream2ArrayList(ArrayList<T> list, Stream<T> stream) {

		AtomicInteger index = new AtomicInteger();
		stream.parallel().forEach((val)->{
			int i = index.getAndIncrement();
			list.set(i, val);
		});
	}

}
