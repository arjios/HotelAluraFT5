package hotel.services.utils;

import java.time.Instant;

public class DateBetween {
	private static Long days;
	private static Integer daysNumber;
	private static Integer dayByUse = 1;

	static Integer DayBetweenByDate(Instant dateChkIn, Instant dateChkOut) {
		if(dateChkOut != null && dateChkIn != null) {
			if(dateChkIn.compareTo(dateChkOut) < 0) {
				days = ((dateChkOut.getEpochSecond() - dateChkIn.getEpochSecond()) / 86400);
				daysNumber = Integer.valueOf(days.intValue());
			} else if((dateChkIn.compareTo(dateChkOut) == 0)) {
				daysNumber = dayByUse;
			}
		} else {
			daysNumber = 0;
		}
		return daysNumber;
	}
}
