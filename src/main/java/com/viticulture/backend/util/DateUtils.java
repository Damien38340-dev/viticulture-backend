package com.viticulture.backend.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String convertTimestampToString(Long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);

        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }

    public static String convertDateToString(Date date) {
        Instant instant = Instant.ofEpochSecond(date.getTime());
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static boolean isLessThanOneHourOld(String dateString) {
        LocalDateTime dataTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
        return Duration.between(dataTime, LocalDateTime.now()).toMinutes() < 60;
    }

}
