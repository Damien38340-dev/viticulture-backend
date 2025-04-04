package com.viticulture.backend.service;

public interface AnalyticsService {

    double calculateAverageTemperature();

    double calculateAverageHumidity();

    double calculateAverageWindSpeed();

    double calculateAveragePressure();

    double calculateTotalPrecipitation();

    double calculateAverageSoilSurfaceTemperature();

    double calculateAverageSoilDepthTemperature();

    double calculateAverageSoilMoisture();
}
