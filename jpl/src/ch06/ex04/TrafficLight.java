package ch06.ex04;

import java.awt.Color;

/**
 * Q.定数固有メソッドを持つことを推奨するか？
 * A.推奨しない
 * 　特に値に応じた処理を行ったりせずに定められたColorオブジェクトを返すだけだから
 */
public enum TrafficLight {

	BLUE(){
		public Color getColor() {
			return Color.BLUE;
		}
	},
	YELLOW(){
		public Color getColor() {
			return Color.YELLOW;
		}
	},
	RED(){
		public Color getColor() {
			return Color.RED;
		}
	},;

	abstract Color getColor();

}
