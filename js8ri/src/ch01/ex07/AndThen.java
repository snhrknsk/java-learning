package ch01.ex07;

public class AndThen {

	public static void main(final String... args) {
        andThen(() -> System.out.println("First message"), () -> System.out.println("Second message")).run();
    }

    private static Runnable andThen(final Runnable first, final Runnable second) {
        return () -> {
            first.run();
            second.run();
        };
    }
}
