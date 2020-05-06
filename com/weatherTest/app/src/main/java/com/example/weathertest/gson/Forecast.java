package com.example.weathertest.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2019/8/10.
 */

public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{
        @SerializedName("tmp_max")
        public String max;
        @SerializedName("tmp_min")
        public String  min;

    }
    public class More{

        @SerializedName("txt_d")
        public String info;
    }
}
