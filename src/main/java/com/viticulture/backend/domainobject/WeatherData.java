package com.viticulture.backend.domainobject;

import lombok.Data;

@Data
public class WeatherData {

    private String city;
    private String date;
    private String weatherCondition;
    private String weatherDescription;
    private String weatherIcon;
    private double temperature;
    private double temperatureMin;
    private double temperatureMax;
    private double clouds;
    private double insolationDuration;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windDirection;
    private double precipitation;
    private double snow;

    public WeatherData(String city, String date, String weatherCondition, String weatherDescription, String weatherIcon, double temperature, double temperatureMin, double temperatureMax, double clouds, double insolationDuration, double humidity, double pressure, double windSpeed, double windDirection, double precipitation, double snow) {
        this.city = city;
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.clouds = clouds;
        this.insolationDuration = insolationDuration;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
        this.snow = snow;
    }

    public WeatherData() {

    }
}
