package ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RepeatAuth {

	private static char[] pass = "secret".toCharArray();

	public static <T> CompletableFuture<T> repeat(Supplier<T> f, Predicate<T> until) {
		return CompletableFuture.supplyAsync(f).thenCompose(val -> {
			if (until.test(val)) {
				return CompletableFuture.completedFuture(val);
			}
			return repeat(f, until);
		});
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Supplier<PasswordAuthentication> f = () -> {
			String username = "";
			String password = "";

			System.out.println("user name : ");
			username = scan.nextLine();
			System.out.println("password: ");
			password = scan.nextLine();
			return new PasswordAuthentication(username, password.toCharArray());
		};
		Predicate<PasswordAuthentication> until = passAuth -> {
			return Arrays.equals(passAuth.getPassword(), pass);
		};

		repeat(f, until).thenAccept(result -> System.out.println(result.getUserName() + " is authenticated"));

		ForkJoinPool.commonPool().awaitQuiescence(30, TimeUnit.SECONDS);
		scan.close();
	}
}
