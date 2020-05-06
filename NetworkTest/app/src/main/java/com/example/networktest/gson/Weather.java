package com.example.networktest.gson;

/**
 * Created by asus on 2019/8/9.
 */

public class Weather {
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Lifestyle lifestyle;

    /*@SerializedName("daily_forecast")
    public List<Forecast> forecastList;*/

}
