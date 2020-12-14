package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DaysSinceBirthday {

	public static long numberOfBirthDays(int year, int month, int day) {
		LocalDate birthday = LocalDate.of(year, month, day);
		LocalDate now = LocalDate.now();
		return ChronoUnit.DAYS.between(birthday, now);
	}

	public static void main(String[] args) {
		System.out.println(numberOfBirthDays(1993, 5, 25) + " days");
	}
}
