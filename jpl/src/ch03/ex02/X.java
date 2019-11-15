package ch03.ex02;

public class X {

	private int dispValue = showX();
	protected int xMask = 0x00ff;
	protected int fullMask;

	public X() {
		System.out.printf("X initialize:\t fullMask %4x, xMask %4x%n",fullMask,xMask);
		fullMask = xMask;
		System.out.printf("X constractor:\t fullMask %4x, xMask %4x%n",fullMask,xMask);
	}
	public int mask(int orig) {
		return (orig & fullMask);
	}

	public int showX() {
		System.out.printf("X def value:\t fullMask %4x, yMask %4x%n",fullMask,xMask);
		return 0;
	}

	public static void main(String...args) {
		Y y = new Y();
	}
}

class Y extends X{

	private int disp = show();
	protected int yMask = 0xff00;

	public int show() {
		System.out.printf("Y def value:\t fullMask %4x, yMask %4x%n",fullMask,yMask);
		return 0;
	}

	public Y() {
		System.out.printf("Y initialize:\t fullMask %4x, yMask %4x%n",fullMask,yMask);
		fullMask |= yMask;
		System.out.printf("Y constractor:\t fullMask %4x, yMask %4x%n",fullMask,yMask);
	}
}
