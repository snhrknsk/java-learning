package chap1.exercise1_4;

public class Prime {
	/*素数を表示*/
	public static void main(String[] args) {
		System.out.println("素数を表示");
		for (int i = 2; i < 100; i++) {
			boolean isPrime = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if ( i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				System.out.println(i);
			}
		}
	}

}
