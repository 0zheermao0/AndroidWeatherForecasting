package edu.neu.weatherforecasting.utils;

import android.content.Context;

import edu.neu.weatherforecasting.utils.formatters.WeatherFormatter;

public class Formatting {

    private Context context;

    public Formatting(Context context) {
        this.context = context;
    }

    public String setWeatherIcon(int actualId, boolean day) {
        return WeatherFormatter.getWeatherIconAsText(actualId, day, context);
    }
}
