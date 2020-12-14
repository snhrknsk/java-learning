package ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {

	private final LocalDateTime start;
	private final LocalDateTime end;

	public TimeInterval(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public boolean isScheduleOverlapped(TimeInterval otherTime) {
		if (start.compareTo(otherTime.end) >= 0) {
			return false;
		} else if (end.compareTo(otherTime.start) <= 0) {
			return false;
		}
		return true;
	}
}
