package com.myprojects.WeatherAPP_POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestPOJO {
    @JsonProperty("weather")
    private WeatherPOJO[] weatherPOJO;

    @JsonProperty("main")
    private MainPOJO mainPOJO;

    @JsonProperty("wind")
    private WindPOJO windPOJO;

    @JsonProperty("clouds")
    private CloudsPOJO cloudsPOJO;

    @JsonProperty("sys")
    private SysPOJO sysPOJO;






    public CloudsPOJO getCloudsPOJO() {
        return cloudsPOJO;
    }

    public void setCloudsPOJO(CloudsPOJO cloudsPOJO) {
        this.cloudsPOJO = cloudsPOJO;
    }

    public WeatherPOJO[] getWeatherPOJO() {
        return weatherPOJO;
    }

    public void setWeatherPOJO(WeatherPOJO[] weatherPOJO) {
        this.weatherPOJO = weatherPOJO;
    }

    public WindPOJO getWindPOJO() {
        return windPOJO;
    }

    public void setWindPOJO(WindPOJO windPOJO) {
        this.windPOJO = windPOJO;
    }

    public SysPOJO getSysPOJO() {
        return sysPOJO;
    }

    public void setSysPOJO(SysPOJO sysPOJO) {
        this.sysPOJO = sysPOJO;
    }

    private long timezone;

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long timezone) {
        this.timezone = timezone;
    }

    public MainPOJO getMainPOJO() {
        return mainPOJO;
    }

    public void setMainPOJO(MainPOJO mainPOJO) {
        this.mainPOJO = mainPOJO;
    }

    @Override
    public String toString() {
        return /*"RequestPOJO{" +
                "weatherPOJO=" + Arrays.toString(weatherPOJO) + "\n"+
                ", mainPOJO=" + mainPOJO +"\n"+
                ", windPOJO=" + windPOJO +"\n"+
                ", sysPOJO=" + sysPOJO +"\n"+
                ", timezone=" + timezone +"\n"+
                '}';*/
        weatherPOJO[0].getMain() +" : " + weatherPOJO[0].getDescription()+"\n"+
               "Temperature,°C : "+ mainPOJO.getTemp()+"\n"+
                "Temperature feels like,°C : " +mainPOJO.getFeels_like()+"\n"+
                "Atmospheric pressure, hPa : " +mainPOJO.getPressure() +"\n"+
                "Humidity, % : " +mainPOJO.getHumidity()+"\n"+
                "Wind speed, meter/sec : "+windPOJO.getSpeed()+"\n"+
                "Cloudiness, % : " +cloudsPOJO.getAll() +"\n"+
                "Sunrise time: " + sysPOJO.unixToDate(sysPOJO.getSunrise(),timezone) +"\n"+
                "Sunset time: " + sysPOJO.unixToDate(sysPOJO.getSunset(),timezone)

                ;
    }
}
