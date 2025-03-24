package com.viticulture.backend.dto;

public record WeatherDataDTO(String date,
                             double temperature,
                             double humidity,
                             double pressure,
                             double windSpeed,
                             String windDirection,
                             double precipitation) {
}
