package com.example.weathertest.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asus on 2019/8/10.
 */

public class Weather {
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Lifestyle lifestyle;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
