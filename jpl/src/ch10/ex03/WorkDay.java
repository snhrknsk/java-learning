package ch10.ex03;

public class WorkDay {

	enum Week {
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY
	}

	public static boolean isWorkDay(Week week) {
		if (week == Week.SUNDAY || week == Week.SATURDAY) {
			return false;
		}
		return true;
	}

	public static boolean isWorkDaySwitch(Week week) {
		switch (week) {
		case SUNDAY:
		case SATURDAY:
			return false;
		default:
			return true;
		}
	}

}
