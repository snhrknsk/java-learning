package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThirteenFriday {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(1901, 1, 13);
		LocalDate end = LocalDate.of(2000, 12, 13);
		while(!date.equals(end.plusMonths(1))) {
			if (date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
				System.out.println(date);
			}
			date = date.plusMonths(1);
		}
	}
}
