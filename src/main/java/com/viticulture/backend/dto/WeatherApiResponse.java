package com.viticulture.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class WeatherApiResponse {

    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;
    private Sys sys;
    private long dt; // Unix timestamp for date
    private int timezone;

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
        private double humidity;
        private double pressure;

    }

    @Data
    public static class Weather {
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Wind {
        private double speed;
        private double deg;  // Wind direction in degrees
    }


    @Data
    public static class Rain {
        @JsonProperty("1h")
        private double oneHour;
    }

    @Data
    public static class Snow {
        @JsonProperty("1h")
        private double oneHour;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Sys {
        private long sunrise;
        private long sunset;
    }

}
