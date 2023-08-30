package hotel.services.utils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CalcDaily {
	public static Double valorDiarias(Double v, Date chkin, Date chkout) {
		LocalDateTime in = chkin.toLocalDate().atStartOfDay();
		LocalDateTime out = chkout.toLocalDate().atStartOfDay();
		return (Double) (v * DateBetween.DayBetweenByDate(in.toInstant(ZoneOffset.UTC), 
															out.toInstant(ZoneOffset.UTC)));
	}
}
