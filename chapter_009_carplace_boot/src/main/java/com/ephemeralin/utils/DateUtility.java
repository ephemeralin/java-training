package com.ephemeralin.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 * The Date utility.
 */
public class DateUtility {
    /**
     * Gives Today period with start date and end date.
     *
     * @return map with two keys: startDate and endDate
     */
    public static HashMap<String, Long> getTodayPeriodInMillis() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        ZonedDateTime zdtStart = zdt.toLocalDate().atStartOfDay(zoneId);
        ZonedDateTime zdtTomorrowStart = zdtStart.plusDays(1);
        HashMap<String, Long> period = new HashMap<>();
        period.put("startDate", zdtStart.toInstant().toEpochMilli());
        period.put("endDate", zdtTomorrowStart.toInstant().toEpochMilli());
        return period;
    }
}
