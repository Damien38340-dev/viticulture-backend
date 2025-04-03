package com.viticulture.backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity

// @Data: Lombok annotation that generates getters, setters, toString(), equals(), and hashCode() methods for you.
@Data
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String date;
    private String weatherCondition;
    private String weatherDescription;
    private String weatherIcon;
    private long sunrise;
    private long sunset;
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

    public WeatherDataEntity(String city, String date, String weatherCondition, String weatherDescription, String weatherIcon, long sunrise, long sunset, double temperature, double temperatureMin, double temperatureMax, double clouds, double insolationDuration, double humidity, double pressure, double windSpeed, double windDirection, double precipitation, double snow) {

        this.city = city;
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.sunrise = sunrise;
        this.sunset = sunset;
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

    public WeatherDataEntity() {

    }
}
