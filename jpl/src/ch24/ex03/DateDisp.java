package ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DateDisp {

	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException();
		}
		Map<String, DateFormat> format = new HashMap<>();
		format.put("SHORT ", DateFormat.getDateInstance(DateFormat.SHORT));
		format.put("MEDIUM", DateFormat.getDateInstance(DateFormat.MEDIUM));
		format.put("LONG  ", DateFormat.getDateInstance(DateFormat.LONG));
		format.put("FULL  ", DateFormat.getDateInstance(DateFormat.FULL));

		for (Entry<String, DateFormat> frm : format.entrySet()) {
			try {
				System.out.print(frm.getKey() +  " : ");
				System.out.println(frm.getValue().parse(args[0]));
			} catch (ParseException e) {
				System.out.println("×");
			}
		}
	}
}
