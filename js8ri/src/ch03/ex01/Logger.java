package ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class Logger {

	public enum LEVEL {
		FINEST
	}

	public static void logIf( LEVEL lev, BooleanSupplier condition ,Supplier<String> message ) {
		if ( condition.getAsBoolean() ) {
			System.out.println( message.get() );
		}
	}

	public static void main(String[] args) {
		String[] a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		int i = 10;
		logIf( LEVEL.FINEST, () -> i == 10, () -> "a[10] = " + a[10] );
	}
}
