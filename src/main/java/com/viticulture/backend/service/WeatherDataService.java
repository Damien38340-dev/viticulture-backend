package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.WeatherData;

import java.util.List;
import java.util.Optional;

public interface WeatherDataService {

    List<WeatherData> getAllWeatherData();

    WeatherData getWeatherDataById(Long id);

    WeatherData saveWeatherData(WeatherData weatherData);

    WeatherData updateWeatherData(WeatherData weatherData, Long id);

    void deleteWeatherData(Long id);

    Optional<WeatherData> getLatestWeatherData(String city);

}
