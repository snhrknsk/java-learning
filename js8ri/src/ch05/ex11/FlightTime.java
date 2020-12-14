package ch05.ex11;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FlightTime {

	/**
	 *
	 * @param departureTime 出発時刻
	 * @param departureZone 出発場所のタイムゾーン
	 * @param destinationTime 到着時刻
	 * @param destinationZone 到着場所のタイムゾーン
	 * @return 飛行時間(秒)
	 */
	public static long calcFlightTime(LocalDateTime departureTime, ZoneId departureZone, LocalDateTime destinationTime, ZoneId destinationZone) {
		long result = ZonedDateTime.of(destinationTime, destinationZone).toEpochSecond() - ZonedDateTime.of(departureTime, departureZone).toEpochSecond();
		return Math.abs(result);
	}

	private static final ZoneId departureZone = ZoneId.of("Europe/Berlin");
	private static final ZoneId destinationZone = ZoneId.of("America/Los_Angeles");

	public static void main(String[] args) {
		System.out.println();
		LocalDateTime deparureTime = LocalDateTime.of(2020, 12, 18, 14, 5);
		LocalDateTime destinationTime = LocalDateTime.of(2020, 12, 18, 16, 40);
		long res = calcFlightTime(deparureTime, departureZone, destinationTime, destinationZone);
		System.out.println(res / 3600 + "時間" + res / 60 % 60);
	}

}
