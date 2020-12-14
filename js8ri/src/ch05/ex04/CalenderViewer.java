package ch05.ex04;

import java.time.LocalDate;

public class CalenderViewer {

	public static void dispCalender(int year, int month) {

		LocalDate date = LocalDate.of(year, month, 1);
		int dayOfWeek = date.getDayOfWeek().getValue();

		for (int day = 1; day < dayOfWeek; day++) {
			System.out.print("   ");
		}
		int day =1;
		do {
			System.out.printf("%3d", day);
			dayOfWeek = date.getDayOfWeek().getValue();
			if (dayOfWeek % 7 ==0) {
				System.out.println();
			}
			date = date.plusDays(1);
			day = date.getDayOfMonth();
		} while(day != 1);
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("2020-12");
		dispCalender(2020, 12);
		System.out.println("2021-1");
		dispCalender(2021, 1);
	}
}
