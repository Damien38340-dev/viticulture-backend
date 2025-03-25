package com.viticulture.backend.domainobject;

import lombok.Data;

@Data
public class WeatherData {

    private String date;
    private double temperature;
    private double temperatureMin;
    private double temperatureMax;
    private double daylightDuration;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windDirection;
    private double precipitation;

    public WeatherData(String date, double temperature, double temperatureMin, double temperatureMax, double daylightDuration, double humidity, double pressure, double windSpeed, double windDirection, double precipitation) {
        this.date = date;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.daylightDuration = daylightDuration;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
    }

    public WeatherData() {

    }
}
