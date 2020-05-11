package ch22.ex01;

public class FloatDisp {

	private static final int LINE = 80;

	public static void showFloats(float[] floats, int cols) {
		//符号(1文字)+科学的記数法(4文字)+4桁の表現=9文字利用し１行80文字のためcolsを最大8列に制限
		if (cols > 8) {
			cols = 8;
		}
		//1要素の文字数計算
		int strLength = LINE / cols;
		for (int i = 0; i < floats.length; i++) {
			System.out.printf("%1$" + strLength + ".4g", floats[i]);
			if (i%cols == cols - 1) {
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		float[] floats = new float[]
				{
				3.141f,
				3141.59265359f,
				0.314159265359f,
				-3.1f,
				-3f,
				3.141592653f,
				0.0000314f,
				31415,
				-3141f,
				-0.0031415926535f,
				3141592.65359f,
				3.14f,
				3.141e+11f,
				0f};
		showFloats(floats, 10);
	}
}
