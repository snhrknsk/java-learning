package ch07.ex03;

public class Pascal {

	static int[][] calcPascal(int row) {
		int[][] result = new int[row][];
		result[0] = new int[1];
		result[0][0] = 1;
		for (int i = 1; i < result.length; i++) {
			result[i] = new int[i + 1];
			for (int j = 0; j < result[i].length; j++) {
				if (j == 0) {
					result[i][j] = 1;
				} else if (j == result[i].length - 1) {
					result[i][j] = result[i - 1][j -1];
				} else {
					result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
				}
			}
		}
		return result;
	}

	static void displayPascal(int row) {
		int[][] pascal = calcPascal(row);
		for (int[] array : pascal) {
			for (int result : array) {
				System.out.printf("%4d,", result);
			}
			System.out.println();
		}
	}

	public static void main(String[]args) {
		Pascal.displayPascal(12);
	}
}
