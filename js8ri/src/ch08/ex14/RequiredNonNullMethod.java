package ch08.ex14;

import java.util.Objects;

public class RequiredNonNullMethod {

	public static void main(String[] args) {
		nullValidation(null);
	}

	public static void nullValidation(String str) {
		// メッセージにメソッド名や引数の何がNullを示すことでメッセージが分かりやすくなる？
		Objects.requireNonNull(str, "\"RequiredNonNullMethod.nullValidation\" method receive null argument. str is null.");
	}
}
