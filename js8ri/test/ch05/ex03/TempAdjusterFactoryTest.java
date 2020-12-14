package ch05.ex03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TempAdjusterFactoryTest {

	@Test
	void testNextWorkDay() {
		LocalDate expected = LocalDate.of(2020, 12, 21);
		LocalDate date = LocalDate.of(2020, 12, 18);
		LocalDate actual = date.with(TempAdjusterFactory.next(w -> w.getDayOfWeek().getValue() < 6));
		assertEquals(expected, actual);
	}

	@Test
	void testNextSunday() {
		LocalDate expected = LocalDate.of(2020, 12, 20);
		LocalDate date = LocalDate.of(2020, 12, 18);
		LocalDate actual = date.with(TempAdjusterFactory.next(w -> w.getDayOfWeek().getValue() == 7));
		assertEquals(expected, actual);
	}

}
