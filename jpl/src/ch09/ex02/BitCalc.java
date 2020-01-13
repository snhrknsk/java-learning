package ch09.ex02;


public class BitCalc {

	private int mask = 1;

	public int bitCount(int x) {
		int count = 0;

		while(x != 0) {
			if ((x & mask) == 1) {
				count++;
			}
			x >>=1;
		}
		return count;
	}

}