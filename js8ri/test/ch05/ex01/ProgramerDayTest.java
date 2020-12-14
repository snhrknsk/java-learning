package ch05.ex01;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ProgramerDayTest {

	@Test
	void testUruu() {
		LocalDate expected = LocalDate.of(2020, 9, 12);
		LocalDate actual = ProgramerDay.calcProgramerDay(2020);
		assertEquals(expected, actual);

	}

	@Test
	void testNormal() {
		LocalDate expected = LocalDate.of(2019, 9, 13);
		LocalDate actual = ProgramerDay.calcProgramerDay(2019);
		assertEquals(expected, actual);
	}

}
