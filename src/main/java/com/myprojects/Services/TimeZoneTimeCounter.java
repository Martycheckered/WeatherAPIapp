package com.myprojects.Services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeZoneTimeCounter {
    private static TimeZoneTimeCounter instance;

    public static  TimeZoneTimeCounter getInstance() {
        if (instance == null) {
            instance = new TimeZoneTimeCounter();
        }
        return instance;
    }

    public String unixToDate (long unixTime, long timeShift) {
        long realLocalTime = unixTime+ timeShift;
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(" HH:mm:ss");

        final String formattedDtm = Instant.ofEpochSecond(realLocalTime)
                .atZone(ZoneId.of("GMT+0"))
                .format(formatter);
        return formattedDtm;
    }

}
