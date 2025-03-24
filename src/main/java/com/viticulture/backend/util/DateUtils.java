package com.viticulture.backend.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String convertTimestampToString(Long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);

        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }

}
