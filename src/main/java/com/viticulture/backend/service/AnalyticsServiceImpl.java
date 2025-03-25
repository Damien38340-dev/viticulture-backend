package com.viticulture.backend.service;

import com.viticulture.backend.entity.WeatherDataEntity;
import com.viticulture.backend.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Override
    public double calculateAverageTemperature() {
        List<WeatherDataEntity> weatherDataList = weatherDataRepository.findAll();
        return weatherDataList.stream()
                .mapToDouble(WeatherDataEntity::getTemperature)
                .average()
                .orElse(0.0);
    }

    @Override
    public double calculateAverageHumidity() {
        List<WeatherDataEntity> weatherDataList = weatherDataRepository.findAll();
        return weatherDataList.stream()
                .mapToDouble(WeatherDataEntity::getHumidity)
                .average()
                .orElse(0.0);
    }

    @Override
    public double calculateAverageWindSpeed() {
        List<WeatherDataEntity> weatherDataList = weatherDataRepository.findAll();
        return weatherDataList.stream()
                .mapToDouble(WeatherDataEntity::getWindSpeed)
                .average()
                .orElse(0.0);
    }

    @Override
    public double calculateAveragePressure() {
        List<WeatherDataEntity> weatherDataList = weatherDataRepository.findAll();
        return weatherDataList.stream()
                .mapToDouble(WeatherDataEntity::getPressure)
                .average()
                .orElse(0.0);
    }

    @Override
    public double calculateTotalPrecipitation() {
        List<WeatherDataEntity> weatherDataList = weatherDataRepository.findAll();
        return weatherDataList.stream()
                .mapToDouble(WeatherDataEntity::getPrecipitation)
                .sum();
    }

    // More complex analytics logic (e.g., trends, comparisons, etc.) can go here

}

