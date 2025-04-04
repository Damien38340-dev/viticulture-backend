package com.viticulture.backend.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static String convertTimestampToString(Long timestamp, int timezoneOffsetSeconds) {
        Instant instant = Instant.ofEpochSecond(timestamp);

        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds));
        ZonedDateTime dateTime = instant.atZone(zoneId);

        return dateTime.format(formatter);
    }

    public static String convertTimestampToStringHoursFormat(Long timestamp, int timezoneOffsetSeconds) {
        Instant instant = Instant.ofEpochSecond(timestamp);

        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds));
        ZonedDateTime dateTime = instant.atZone(zoneId);

        DateTimeFormatter formatterToHour = DateTimeFormatter.ofPattern("HH:mm:ss");

        return dateTime.format(formatterToHour);
    }

    public static String convertDateToString(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault()); // UTC Time
        return dateTime.format(formatter);
    }

    public static boolean isLessThanOneHourOld(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());

        return Duration.between(zonedDateTime, ZonedDateTime.now()).toMinutes() < 60;
    }

}
