package ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import ch10.ex03.WorkDay.Week;

public class WorkDayTest {

	@Test
	public void test() {
		boolean expected = true;
		assertEquals(expected, WorkDay.isWorkDay(Week.MONDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.MONDAY));
		assertEquals(expected, WorkDay.isWorkDay(Week.TUESDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.TUESDAY));
		assertEquals(expected, WorkDay.isWorkDay(Week.WEDNESDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.WEDNESDAY));
		assertEquals(expected, WorkDay.isWorkDay(Week.THURSDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.THURSDAY));
		assertEquals(expected, WorkDay.isWorkDay(Week.FRIDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.FRIDAY));
		expected = false;
		assertEquals(expected, WorkDay.isWorkDay(Week.SATURDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.SATURDAY));
		assertEquals(expected, WorkDay.isWorkDay(Week.SUNDAY));
		assertEquals(expected, WorkDay.isWorkDaySwitch(Week.SUNDAY));

	}

}
