package com.viticulture.backend.dto;

public record WeatherDataDTO(String city,
                             String date,
                             double temperature,
                             double temperatureMin,
                             double temperatureMax,
                             double daylightDuration,
                             double humidity,
                             double pressure,
                             double windSpeed,
                             double windDirection,
                             double precipitation) {
}