package ch22.ex15;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

	/**
	 * 逆ポーランド形式の文字列の演算を行う
	 * @param source 逆ポーランド形式(各要素間は空白)
	 * @return
	 */
	public static double calc(String source) {

		String[] stringArray = source.split("\\s");
		Deque<Double> que = new ArrayDeque<>();
		double a = 0;
		double b = 0;
		for (int i = 0; i < stringArray.length; i++) {
			switch (stringArray[i]) {
			case "+":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(b + a);
				break;
			case "-":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(b - a);
				break;
			case "/":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(b / a);
				break;
			case "*":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(b * a);
				break;
			case "%":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(b % a);
				break;
			case "sin":
				a = que.pollFirst();
				que.addFirst(Math.sin(a));
				break;
			case "cos":
				a = que.pollFirst();
				que.addFirst(Math.cos(a));
				break;
			case "tan":
				a = que.pollFirst();
				que.addFirst(Math.tan(a));
				break;
			case "asin":
				a = que.pollFirst();
				que.addFirst(Math.asin(a));
				break;
			case "acos":
				a = que.pollFirst();
				que.addFirst(Math.acos(a));
				break;
			case "atan":
				a = que.pollFirst();
				que.addFirst(Math.atan(a));
				break;
			case "atan2":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(Math.atan2(b, a));
				break;
			case "toRadian":
				a = que.pollFirst();
				que.addFirst(Math.toRadians(a));
				break;
			case "toDegrees":
				a = que.pollFirst();
				que.addFirst(Math.toDegrees(a));
				break;
			case "exp":
				a = que.pollFirst();
				que.addFirst(Math.exp(a));
				break;
			case "sinh":
				a = que.pollFirst();
				que.addFirst(Math.sinh(a));
				break;
			case "cosh":
				a = que.pollFirst();
				que.addFirst(Math.cosh(a));
				break;
			case "tanh":
				a = que.pollFirst();
				que.addFirst(Math.tanh(a));
				break;
			case "pow":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(Math.pow(b, a));
				break;
			case "log":
				a = que.pollFirst();
				que.addFirst(Math.log(a));
				break;
			case "log10":
				a = que.pollFirst();
				que.addFirst(Math.log10(a));
				break;
			case "sqrt":
				a = que.pollFirst();
				que.addFirst(Math.sqrt(a));
				break;
			case "cbrt":
				a = que.pollFirst();
				que.addFirst(Math.cbrt(a));
				break;
			case "signum":
				a = que.pollFirst();
				que.addFirst(Math.signum(a));
				break;
			case "ceil":
				a = que.pollFirst();
				que.addFirst(Math.ceil(a));
				break;
			case "floor":
				a = que.pollFirst();
				que.addFirst(Math.floor(a));
				break;
			case "rint":
				a = que.pollFirst();
				que.addFirst(Math.rint(a));
				break;
			case "round":
				a = que.pollFirst();
				que.addFirst(Double.parseDouble(String.valueOf(Math.round(a))));
				break;
			case "abs":
				a = que.pollFirst();
				que.addFirst(Math.abs(a));
				break;
			case "max":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(Math.max(b, a));
				break;
			case "min":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(Math.min(b, a));
				break;
			case "hypot":
				a = que.pollFirst();
				b = que.pollFirst();
				que.addFirst(Math.hypot(b, a));
				break;
			default:
			    que.addFirst(Double.parseDouble(stringArray[i]));
			}
		}
		return que.pop();
	}
}
