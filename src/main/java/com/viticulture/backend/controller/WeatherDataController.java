package com.viticulture.backend.controller;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.dto.WeatherDataDTO;
import com.viticulture.backend.service.WeatherApiService;
import com.viticulture.backend.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private WeatherApiService weatherApiService;

    @GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherDataService.getAllWeatherData();
    }

    @GetMapping("/{id}")
    public WeatherData getWeatherData(@PathVariable Long id) {
        return weatherDataService.getWeatherDataById(id);
    }

    @PostMapping
    public WeatherData createWeatherData(@RequestBody WeatherDataDTO weatherDataDTO) {
        WeatherData weatherDataEntityToBeSaved = new WeatherData(
                weatherDataDTO.city(),
                weatherDataDTO.date(),
                weatherDataDTO.weatherCondition(),
                weatherDataDTO.weatherDescription(),
                weatherDataDTO.weatherIcon(),
                weatherDataDTO.sunrise(),
                weatherDataDTO.sunset(),
                weatherDataDTO.temperature(),
                weatherDataDTO.temperatureMin(),
                weatherDataDTO.temperatureMax(),
                weatherDataDTO.clouds(),
                weatherDataDTO.daylightDuration(),
                weatherDataDTO.humidity(),
                weatherDataDTO.pressure(),
                weatherDataDTO.windSpeed(),
                weatherDataDTO.windDirection(),
                weatherDataDTO.precipitation(),
                weatherDataDTO.snow()
        );

        return weatherDataService.saveWeatherData(weatherDataEntityToBeSaved);
    }

    @PatchMapping("/{id}")
    public WeatherData updateWeatherData(@PathVariable Long id, @RequestBody WeatherDataDTO weatherDataDTO) {
        WeatherData weatherDataEntityToBeUpdated = new WeatherData(
                weatherDataDTO.city(),
                weatherDataDTO.date(),
                weatherDataDTO.weatherCondition(),
                weatherDataDTO.weatherDescription(),
                weatherDataDTO.weatherIcon(),
                weatherDataDTO.sunrise(),
                weatherDataDTO.sunset(),
                weatherDataDTO.temperature(),
                weatherDataDTO.temperatureMin(),
                weatherDataDTO.temperatureMax(),
                weatherDataDTO.clouds(),
                weatherDataDTO.daylightDuration(),
                weatherDataDTO.humidity(),
                weatherDataDTO.pressure(),
                weatherDataDTO.windSpeed(),
                weatherDataDTO.windDirection(),
                weatherDataDTO.precipitation(),
                weatherDataDTO.snow()
        );
        return weatherDataService.updateWeatherData(weatherDataEntityToBeUpdated, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeatherData(@PathVariable Long id) {
        weatherDataService.deleteWeatherData(id);
        return ResponseEntity.ok("Weather Data with ID : " + id + " deleted successfully");
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<WeatherData> getWeatherDataByCity(@PathVariable String city) {
        return ResponseEntity.ok(weatherApiService.fetchWeatherData(city));
    }

    @GetMapping("/forecast/city/{city}")
    public ResponseEntity<List<WeatherData>> getForecastData(@PathVariable String city) {
        return  ResponseEntity.ok(weatherApiService.fetchForecastByCity(city));
    }
}
