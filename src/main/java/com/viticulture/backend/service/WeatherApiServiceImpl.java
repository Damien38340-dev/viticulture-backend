package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.dto.ForecastApiResponse;
import com.viticulture.backend.dto.WeatherApiResponse;
import com.viticulture.backend.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                response.getClouds() != null ? response.getClouds().getAll() : 0);

        return new WeatherData(
                formatCityName(city),
                DateUtils.convertTimestampToString(response.getDt()),
                response.getWeather().get(0).getMain(),
                response.getWeather().get(0).getDescription(),
                response.getWeather().get(0).getIcon(),
                response.getSys().getSunrise(),
                response.getSys().getSunset(),
                response.getMain().getTemp(),
                response.getMain().getTemp_min(),
                response.getMain().getTemp_max(),
                response.getClouds() != null ? response.getClouds().getAll() : 0,
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
    public List<WeatherData> fetchForecastByCity(String city) {

        ForecastApiResponse response = restTemplate.getForObject(buildForecastApiUrl(city), ForecastApiResponse.class);

        double adjustedInsolationDuration = getInsolationDuration(
                response.getCity().getSunrise(),
                response.getCity().getSunset(),
                response.getForecasts().get(0).getClouds() != null ? response.getForecasts().get(0).getClouds().getAll() : 0);

        return response.getForecasts().stream()
                .map(forecast -> new WeatherData(
                        formatCityName(response.getCity().getName()),
                        DateUtils.convertTimestampToString(forecast.getDt()),
                        forecast.getWeather() != null && !forecast.getWeather().isEmpty() ? forecast.getWeather().get(0).getMain() : "Unknown",
                        forecast.getWeather() != null && !forecast.getWeather().isEmpty() ? forecast.getWeather().get(0).getDescription() : "No description",
                        forecast.getWeather() != null && !forecast.getWeather().isEmpty() ? forecast.getWeather().get(0).getIcon() : "default_icon",
                        response.getCity().getSunrise(),
                        response.getCity().getSunset(),
                        forecast.getMain().getTemp(),
                        forecast.getMain().getTemp_min(),
                        forecast.getMain().getTemp_max(),
                        forecast.getClouds() != null ? forecast.getClouds().getAll() : 0,
                        adjustedInsolationDuration,
                        forecast.getMain().getHumidity(),
                        forecast.getMain().getPressure(),
                        forecast.getWind() != null ? forecast.getWind().getSpeed() : 0.0,
                        forecast.getWind() != null ? forecast.getWind().getDeg() : 0.0,
                        forecast.getRain() != null ? forecast.getRain().getThreeHour() : 0.0,
                        forecast.getSnow() != null ? forecast.getSnow().getOneHour() : 0.0
                ))
                .collect(Collectors.toList());
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
