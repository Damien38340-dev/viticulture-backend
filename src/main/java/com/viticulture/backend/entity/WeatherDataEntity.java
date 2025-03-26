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
    private double temperature;
    private double temperatureMin;
    private double temperatureMax;
    private double insolationDuration;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windDirection;
    private double precipitation;

    public WeatherDataEntity(String city, String date, double temperature, double temperatureMin, double temperatureMax, double insolationDuration, double humidity, double pressure, double windSpeed, double windDirection, double precipitation) {
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

    public WeatherDataEntity() {

    }
}
