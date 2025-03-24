package com.viticulture.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class WeatherApiResponse {

    private Main main;
    private Wind wind;
    private Rain rain;
    private long dt; // Unix timestamp for date

    @Data
    public static class Main {
        private double temp;
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

}
