package com.example.gsontest.gsonWeather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asus on 2019/8/10.
 */

public class Weather {
    public Now now;
    public Basic basic;
    public Update update;
    public String status;
    //public Lifestyle lifestyle;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

    @SerializedName("lifestyle")
    public List<Lifestyle>lifestyles;


}
