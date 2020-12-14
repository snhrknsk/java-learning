package ch05.ex10;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightTime {

	/**
	 *
	 * @param departureTime 出発時刻
	 * @param departureZone 出発場所のタイムゾーン
	 * @param destZone 行先のタイムゾーン
	 * @param flightHour 飛行時間(時間)
	 * @param flightMinute 飛行時間(分)
	 * @return 行先の到着時間
	 */
	public static LocalDateTime destTime(LocalDateTime departureTime, ZoneId departureZone, ZoneId destZone, int flightHour, int flightMinute) {
		return ZonedDateTime.of(departureTime, departureZone).plusHours(flightHour).plusMinutes(flightMinute).withZoneSameInstant(destZone).toLocalDateTime();
	}

	private static final ZoneId departureZone = ZoneId.of("America/Los_Angeles");
	private static final ZoneId destinationZone = ZoneId.of("Europe/Berlin");

	public static void main(String[] args) {
		LocalDateTime time = destTime(LocalDateTime.of(2020, 12, 18, 0, 0), departureZone, destinationZone, 3, 50);
		System.out.println(time);
	}
}
