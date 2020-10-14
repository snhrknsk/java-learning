package ch02.ex08;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class StreamAlternately {

	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){

		List<T> firstList =  first.collect(Collectors.toList());
		List<T> secondList = second.collect(Collectors.toList());
		Builder<T> stream = Stream.builder();
		int i = 0;
		while(true) {
			//要素数がInteger.Maxより大きくOverflowした場合、正しく実行できないのでTryCatchで行う
			try {
				if (i % 2 == 0) {
					stream.add(firstList.get(i));
				} else {
					stream.add(secondList.get(i));
				}
			} catch (Exception e) {
				break;
			}
			i++;
		}
		return stream.build();
	}

	public static void main(String[] args) {
		zip(Stream.of(1,3,5,7,9), Stream.of(2,4,6,8,10,12,14,16)).forEach(System.out::println);
	}
}
