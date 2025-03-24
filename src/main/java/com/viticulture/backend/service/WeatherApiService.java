package com.viticulture.backend.service;


import com.viticulture.backend.domainobject.WeatherData;

public interface WeatherApiService {

    WeatherData fetchWeatherData(String city);


}
