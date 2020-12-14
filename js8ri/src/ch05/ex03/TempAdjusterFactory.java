package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class TempAdjusterFactory {

	public static TemporalAdjuster next(Predicate<LocalDate> p) {
		return TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate result = w;
			do {
				result = result.plusDays(1);
			} while(! p.test(result));
			return result;
		});
	}
}
