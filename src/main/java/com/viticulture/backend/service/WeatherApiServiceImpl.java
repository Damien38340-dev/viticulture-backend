package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.dto.WeatherApiResponse;
import com.viticulture.backend.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData fetchWeatherData(String city) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric") // Get temperature in Celsius
                .toUriString();

        // HTTP request
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        assert response != null;

        return new WeatherData(
                DateUtils.convertTimestampToString(response.getDt()),
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                response.getMain().getPressure(),
                response.getWind().getSpeed(),
                response.getWind().getDeg(),
                response.getRain() != null ? response.getRain().getOneHour() : 0
        );
    }
}
