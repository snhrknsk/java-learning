package ch05.ex02;

import java.time.LocalDate;

public class AddYear {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2000, 2, 29);
		System.out.println("Add 1 year \t:" + date.plusYears(1));
		System.out.println("Add 4 year \t:" + date.plusYears(4));
		System.out.println("Add 1 year 4 times\t:" + date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
