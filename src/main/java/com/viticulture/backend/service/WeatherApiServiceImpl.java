package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.dto.WeatherApiResponse;
import com.viticulture.backend.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.Optional;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public WeatherData fetchWeatherData(String city) {

        Optional<WeatherData> latestWeather = weatherDataService.getLatestWeatherData(city);

        if (latestWeather.isPresent() && DateUtils.isLessThanOneHourOld(latestWeather.get().getDate())) {
            return latestWeather.get();
        }

        WeatherData weatherData = fetchWeatherDataFromApi(city);

        return weatherDataService.saveWeatherData(weatherData);
    }

    @Override
    public WeatherData fetchWeatherDataFromApi(String city) {
        WeatherApiResponse response = restTemplate.getForObject(buildWeatherApiUrl(city), WeatherApiResponse.class);

        double adjustedInsolationDuration = getInsolationDuration(
                response.getSys().getSunrise(),
                response.getSys().getSunset(),
                response.getClouds().getAll());

        return new WeatherData(
                formatCityName(city),
                DateUtils.convertTimestampToString(response.getDt()),
                response.getWeather().getMain(),
                response.getWeather().getDescription(),
                response.getWeather().getIcon(),
                response.getMain().getTemp(),
                response.getMain().getTemp_min(),
                response.getMain().getTemp_max(),
                response.getClouds().getAll(),
                adjustedInsolationDuration,
                response.getMain().getHumidity(),
                response.getMain().getPressure(),
                response.getWind().getSpeed(),
                response.getWind().getDeg(),
                response.getRain() != null ? response.getRain().getOneHour() : 0,
                response.getSnow() != null ? response.getSnow().getOneHour() : 0
        );
    }

    @Override
    public WeatherData fetchForecastByCity(String city) {

        WeatherApiResponse response = restTemplate.getForObject(buildForecastApiUrl(city), WeatherApiResponse.class);

        double adjustedInsolationDuration = getInsolationDuration(
                response.getSys().getSunrise(),
                response.getSys().getSunset(),
                response.getClouds().getAll());

        return new WeatherData(
                formatCityName(city),
                DateUtils.convertTimestampToString(response.getDt()),
                response.getWeather().getMain(),
                response.getWeather().getDescription(),
                response.getWeather().getIcon(),
                response.getMain().getTemp(),
                response.getMain().getTemp_min(),
                response.getMain().getTemp_max(),
                response.getClouds().getAll(),
                adjustedInsolationDuration,
                response.getMain().getHumidity(),
                response.getMain().getPressure(),
                response.getWind().getSpeed(),
                response.getWind().getDeg(),
                response.getRain() != null ? response.getRain().getOneHour() : 0,
                response.getSnow() != null ? response.getSnow().getOneHour() : 0
        );
    }



    private String buildWeatherApiUrl(String city) {
        return UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
    }

    private String buildForecastApiUrl(String city) {
        return UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
    }

    private double getInsolationDuration(long sunriseTimestamp, long sunsetTimestamp, double cloudCover) {
        long insolationDuration = ((sunsetTimestamp - sunriseTimestamp) / 3600); // Convert to hours
        return Math.round(insolationDuration * (1 - cloudCover / 100.0)); // Adjusting with cloud cover
    }

    private String formatCityName(String city) {
        return city.trim().replace(" ", "+");
    }

}
