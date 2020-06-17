package ch24.ex02;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class GlobalCurrency {

	private static List<Locale> localeList = new ArrayList<Locale>() {
		{
			add(Locale.JAPAN);
			add(Locale.CHINA);
			add(Locale.US);
			add(Locale.FRANCE);
			add(Locale.GERMANY);
			add(Locale.CANADA);
		}
	};

	public static void main(String[] args) {

		for (Locale locale : localeList) {
			Currency currency = Currency.getInstance(locale);
			System.out.println(currency.getCurrencyCode() + " : " + currency.getSymbol(locale));
		}
	}

}
