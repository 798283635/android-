package com.example.gsontest.gsonAir;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2019/8/11.
 */

public class Air {
    public Basic basic;

    @SerializedName("air_now_city")
    public AirNowCity airNowCity;
}
