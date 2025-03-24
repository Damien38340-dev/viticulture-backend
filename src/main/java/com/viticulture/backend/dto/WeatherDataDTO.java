package com.viticulture.backend.dto;

public record WeatherDataDTO(String date,
                             double temperature,
                             double humidity,
                             double pressure,
                             double windSpeed,
                             double windDirection,
                             double precipitation) {
}
