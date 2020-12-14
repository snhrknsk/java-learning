package ch05.ex08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class CalcTimeOffset {

	public static void main(String[] args) {
		for (String id : ZoneId.getAvailableZoneIds()) {
			ZoneOffset offset = Instant.now().atZone(ZoneId.of(id)).getOffset();
			System.out.println(id + " " + offset);
		}
	}
}
