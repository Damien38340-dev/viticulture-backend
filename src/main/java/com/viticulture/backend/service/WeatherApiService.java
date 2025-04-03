package com.viticulture.backend.service;


import com.viticulture.backend.domainobject.WeatherData;

import java.util.List;

public interface WeatherApiService {

    WeatherData fetchWeatherData(String city);

    WeatherData fetchWeatherDataFromApi(String city);

    List<WeatherData> fetchForecastByCity(String city);


}
