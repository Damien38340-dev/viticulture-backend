package com.viticulture.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastApiResponse {

    private String cod; // status code returned by the OpenWeather API (200,404...)
    private int message;
    private int cnt; // number of forecast entries returned by the API for a particular request. It's the count of forecast periods in the response. Example: a forecast for the next 5 days with 3-hour intervals, the cnt would be 40 (because 5 days × 8 intervals = 40).
    private City city;

    @JsonProperty("list")
    private List<Forecast> forecasts;

    @Data
    public static class Forecast {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Rain rain;
        private Snow snow;
        private Sys sys;

        @JsonProperty("dt_txt")
        private String dateText;
    }

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
        private double deg;
    }

    @Data
    public static class Rain {
        @JsonProperty("1h")
        private Double oneHour;
    }

    @Data
    public static class Snow {
        @JsonProperty("1h")
        private Double oneHour;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Sys {
        private String pod;
    }

    @Data
    public static class City {
        private long id;
        private String name;
        private Coord coord;
        private String country;
        private long population;
        private long timezone;
        private long sunrise;
        private long sunset;
    }

    @Data
    public static class Coord {
        private double lat;
        private double lon;
    }
}
