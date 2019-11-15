package ch03.ex03;

public class X {

	/*final宣言することでオブジェクト生成中でも利用可能*/
	protected final int xMask = 0x00ff;
	protected int fullMask;

	public X() {
		fullMask = xMask;
	}
	public int mask(int orig) {
		return (orig & fullMask);
	}

	public static void main(String...args) {
		Y y = new Y();
	}
}

class Y extends X{
	protected final int yMask = 0xff00;

	public Y() {
		fullMask |= yMask;
	}
}
