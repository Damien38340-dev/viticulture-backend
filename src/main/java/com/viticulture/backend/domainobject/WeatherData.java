package com.viticulture.backend.domainobject;

import lombok.Data;

@Data
public class WeatherData {

    private String city;
    private String date;
    private double temperature;
    private double temperatureMin;
    private double temperatureMax;
    private double insolationDuration;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windDirection;
    private double precipitation;

    public WeatherData(String city, String date, double temperature, double temperatureMin, double temperatureMax, double insolationDuration, double humidity, double pressure, double windSpeed, double windDirection, double precipitation) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.insolationDuration = insolationDuration;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
    }

    public WeatherData() {

    }
}
