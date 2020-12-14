package ch05.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeZoneFinder {

	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds().stream().filter(s -> {
			ZoneOffset offset = Instant.now().atZone(ZoneId.of(s)).getOffset();
			return Math.abs(offset.getTotalSeconds())< 3600;
		}).forEach(System.out::println);
	}
}
