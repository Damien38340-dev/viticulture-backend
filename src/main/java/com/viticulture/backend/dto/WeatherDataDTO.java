package com.viticulture.backend.dto;

public record WeatherDataDTO(String city,
                             String date,
                             String weatherCondition,
                             String weatherDescription,
                             String weatherIcon,
                             double temperature,
                             double temperatureMin,
                             double temperatureMax,
                             double clouds,
                             double daylightDuration,
                             double humidity,
                             double pressure,
                             double windSpeed,
                             double windDirection,
                             double precipitation,
                             double snow) {
}