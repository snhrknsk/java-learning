package ch05.ex07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TimeIntervalTest {

    @Test
    public void noOverlappe2est() {
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusSeconds(1);

		LocalDateTime start2 = end.plusMinutes(1);
		LocalDateTime end2 = start2.plusSeconds(1);

		TimeInterval schedule1 = new TimeInterval(start, end);
		TimeInterval schedule2 = new TimeInterval(start2, end2);

		assertFalse(schedule1.isScheduleOverlapped(schedule2));
		assertFalse(schedule2.isScheduleOverlapped(schedule1));
    }

    @Test
    public void overlappeTest() {
		//完全にカバー(10-11時と9-12時)
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusHours(1);

		LocalDateTime start2 = start.plusMinutes(1);
		LocalDateTime end2 = start2.plusMinutes(30);

		TimeInterval schedule1 = new TimeInterval(start, end);
		TimeInterval schedule2 = new TimeInterval(start2, end2);

		assertTrue(schedule1.isScheduleOverlapped(schedule2));
		assertTrue(schedule2.isScheduleOverlapped(schedule1));
    }

    @Test
    public void overlappe2Test() {
    	//部分重複(10-12時と11-14時)
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusHours(1);

		LocalDateTime start2 = start.plusMinutes(1);
		LocalDateTime end2 = start2.plusHours(1);

		TimeInterval schedule1 = new TimeInterval(start, end);
		TimeInterval schedule2 = new TimeInterval(start2, end2);

		assertTrue(schedule1.isScheduleOverlapped(schedule2));
		assertTrue(schedule2.isScheduleOverlapped(schedule1));
    }

}
