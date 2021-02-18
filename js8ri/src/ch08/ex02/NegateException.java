package ch08.ex02;

public class NegateException {

	public static void main(String[] args) {

		try {
			// MIN_VALUE=-2147483648を反転すると2147483648でMAX_VALUEを超えるため
			Math.negateExact(Integer.MIN_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
