package com.myprojects.WeatherAPP_POJOs;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SysPOJO {
    private long sunrise;
    private long sunset;

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
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

    @Override
    public String toString() {
        return "SysPOJO{" +
                "sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
