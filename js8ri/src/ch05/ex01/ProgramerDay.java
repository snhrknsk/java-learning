package ch05.ex01;

import java.time.LocalDate;
import java.time.Period;

public class ProgramerDay {

	public static LocalDate calcProgramerDay(int year) {
		return LocalDate.of(year, 1, 1).plus(Period.ofDays(255));
	}

	public static void main(String[] args) {
		LocalDate programerDay = calcProgramerDay(2020);
		System.out.println(programerDay);
	}
}
