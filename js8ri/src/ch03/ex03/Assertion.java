package ch03.ex03;

import java.util.function.BooleanSupplier;

public class Assertion {
	private final static boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");

	/**
	 * Debugモードの時のみ実行しエラーを投げる
	 */
	public static void assertion(BooleanSupplier condition, String message) throws RuntimeException {
		if ( isDebug && condition.getAsBoolean()) {
			throw new RuntimeException(message);
		}
	}

	public static void main(String[] args) {
		assertion( () -> true, "error");
	}
}
