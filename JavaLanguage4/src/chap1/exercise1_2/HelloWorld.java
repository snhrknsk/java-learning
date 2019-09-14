package chap1.exercise1_2;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello, world");
		/*ダブルクオート付け忘れ
		 HelloWorld.java:6: エラー: 文字列リテラルが閉じられていません
                System.out.println("Hello, world);                           ^
		 HelloWorld.java:6: エラー: ';'がありません
                System.out.println("Hello, world);
		 */
		/*メソッド名
		 HelloWorld.java:6: エラー: シンボルを見つけられません
                System.outs.println("Hello, world");
		 */
	}
}
/*}閉じ忘れ
 HelloWorld.java:13: エラー: 構文解析中にファイルの終わりに移りました
 */

