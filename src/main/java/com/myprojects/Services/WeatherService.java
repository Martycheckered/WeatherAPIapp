package com.myprojects.Services;

import com.myprojects.HttpClient;

public class WeatherService {
    private static WeatherService instance;
    private WeatherService () {};

    public static  WeatherService getInstance() {
        if (instance == null) {
            instance = new WeatherService();
        }
        return instance;
    }

    public  String doJob (String fieldContent) {
        String jsonResponce = HttpClient.throwRequest(fieldContent);

        return jsonResponce;
    }
}
