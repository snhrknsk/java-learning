package ch24.ex01;

import java.util.ResourceBundle;

public class GlobalHello {

	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir"));
		//Eclipse上では/jpl/上で実行されるためch24.ex01追記
		ResourceBundle res = ResourceBundle.getBundle("ch24.ex01.GlobalRes");
		String msg;
		if (args.length > 0) {
			msg = res.getString(GlobalRes.GOODBYE);
		} else {
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);

		res = ResourceBundle.getBundle("ch24.ex01.GlobalRes_en");
		if (args.length > 0) {
			msg = res.getString(GlobalRes.GOODBYE);
		} else {
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);

		res = ResourceBundle.getBundle("ch24.ex01.GlobalRes_en_AU");
		if (args.length > 0) {
			msg = res.getString(GlobalRes.GOODBYE);
		} else {
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);

	}
}
