package ch08.ex09;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ScanUtil {

	public static Stream<String> wordStream(Scanner scanner) {
	    return createStream(scanner);
	}

	public static Stream<String> lineStream(Scanner scanner) {
		Iterator<String> iterator = new Iterator<String>() {
			public String next() {
				return scanner.nextLine();
			}
			public boolean hasNext() {
				return scanner.hasNextLine();
			}
		};
		return createStream(iterator);
	}

	public static IntStream intStream(Scanner scanner) {
		Iterator<Integer> iterator = new Iterator<Integer>() {
			public Integer next() {
				return scanner.nextInt();
			}
			public boolean hasNext() {
				return scanner.hasNextInt();
			}
		};
		return createStream(iterator).mapToInt(v -> v.intValue());
	}

	public static DoubleStream doubleStream(Scanner scanner) {
	    Iterator<Double> iterator = new Iterator<Double>() {
	        public Double next() {
	            return scanner.nextDouble();
	        }
	        public boolean hasNext() {
	            return scanner.hasNextDouble();
	        }
	    };
	    return createStream(iterator).mapToDouble(v -> v.doubleValue());
	}

	private static <T> Stream<T> createStream(Iterator<T> iterator) {
		Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
		return StreamSupport.stream(spliterator, false);
	}

}
