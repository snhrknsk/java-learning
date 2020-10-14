package ch02.ex07;

import java.util.stream.Stream;

/**
 * 無限ストリームだとプログラムが終了しないため実装できない
 */
public class StreamFinit {

	public static <T> boolean isFinit(Stream<T> stream) {

		/*
		try {
			stream.toArray();
		} catch (Exception e) {
			return true;
		}
		return false;
		*/
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {

		isFinit(Stream.iterate(0, x -> (x)));
	}
}
