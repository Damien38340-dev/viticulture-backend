package com.viticulture.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class WeatherApiResponse {

    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Sys sys;
    private long dt; // Unix timestamp for date

    @Data
    public static class Main {
        private double temp;
        private double temp_min;
        private double temp_max;
        private double humidity;
        private double pressure;

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
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Sys {
        private long sunrise;
        private long sunset;
    }

}
