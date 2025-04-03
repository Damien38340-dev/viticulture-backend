package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.entity.WeatherDataEntity;
import com.viticulture.backend.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Override
    public List<WeatherData> getAllWeatherData() {
        List<WeatherDataEntity> weatherDataEntities = weatherDataRepository.findAll();

        return weatherDataEntities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public WeatherData getWeatherDataById(Long id) {

        WeatherDataEntity weatherDataEntity = weatherDataRepository.getReferenceById(id);

        return convertToDomain(weatherDataEntity);
    }

    @Override
    public WeatherData saveWeatherData(WeatherData weatherData) {

        WeatherDataEntity weatherDataEntity = convertToEntity(weatherData);

        weatherDataRepository.save(weatherDataEntity);

        return weatherData;
    }

    @Override
    public WeatherData updateWeatherData(WeatherData weatherData, Long id) {

        WeatherDataEntity weatherDataEntityToBeChanged = weatherDataRepository.getReferenceById(id);

        weatherDataEntityToBeChanged.setDate(weatherData.getDate());
        weatherDataEntityToBeChanged.setHumidity(weatherData.getHumidity());
        weatherDataEntityToBeChanged.setPressure(weatherData.getPressure());
        weatherDataEntityToBeChanged.setPrecipitation(weatherData.getPrecipitation());
        weatherDataEntityToBeChanged.setTemperature(weatherData.getTemperature());
        weatherDataEntityToBeChanged.setWindSpeed(weatherData.getWindSpeed());
        weatherDataEntityToBeChanged.setWindDirection(weatherData.getWindDirection());

        weatherDataRepository.save(weatherDataEntityToBeChanged);

        return weatherData;
    }

    @Override
    public void deleteWeatherData(Long id) {
        weatherDataRepository.deleteById(id);
    }

    private WeatherData convertToDomain(WeatherDataEntity weatherDataEntity) {
        return new WeatherData(
                weatherDataEntity.getCity(),
                weatherDataEntity.getDate(),
                weatherDataEntity.getWeatherCondition(),
                weatherDataEntity.getWeatherDescription(),
                weatherDataEntity.getWeatherIcon(),
                weatherDataEntity.getTemperature(),
                weatherDataEntity.getTemperatureMin(),
                weatherDataEntity.getTemperatureMax(),
                weatherDataEntity.getClouds(),
                weatherDataEntity.getInsolationDuration(),
                weatherDataEntity.getHumidity(),
                weatherDataEntity.getPressure(),
                weatherDataEntity.getWindSpeed(),
                weatherDataEntity.getWindDirection(),
                weatherDataEntity.getPrecipitation(),
                weatherDataEntity.getSnow()
        );
    }

    private WeatherDataEntity convertToEntity(WeatherData weatherData) {
        return new WeatherDataEntity(
                weatherData.getCity(),
                weatherData.getDate(),
                weatherData.getWeatherCondition(),
                weatherData.getWeatherDescription(),
                weatherData.getWeatherIcon(),
                weatherData.getTemperature(),
                weatherData.getTemperatureMin(),
                weatherData.getTemperatureMax(),
                weatherData.getClouds(),
                weatherData.getInsolationDuration(),
                weatherData.getHumidity(),
                weatherData.getPressure(),
                weatherData.getWindSpeed(),
                weatherData.getWindDirection(),
                weatherData.getPrecipitation(),
                weatherData.getSnow()
        );
    }

    public Optional<WeatherData> getLatestWeatherData(String city) {
        return weatherDataRepository.findTopByCityOrderByDateDesc(city)
                .map(this::convertToDomain);
    }
}
