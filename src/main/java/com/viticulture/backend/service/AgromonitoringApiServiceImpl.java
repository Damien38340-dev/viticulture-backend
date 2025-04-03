package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.SoilData;
import com.viticulture.backend.dto.SoilApiResponse;
import com.viticulture.backend.util.DateUtils;
import com.viticulture.backend.util.UnitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AgromonitoringApiServiceImpl implements AgromonitoringApiService {

    @Value("${agromonitoring.api.key}")
    private String apiKey;

    @Autowired
    private final RestTemplate restTemplate;

    public AgromonitoringApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private SoilDataService soilDataService;

    @Override
    public SoilData getSoilData(String polyId) {
        Optional<SoilData> latestSoilData = soilDataService.getLatestSoilData(polyId);

        if (latestSoilData.isPresent() && DateUtils.isLessThanOneHourOld(latestSoilData.get().getDate())) {
            return latestSoilData.get();
        }

        SoilData soilData = getSoilDataFromApi(polyId);

        return soilDataService.saveSoilData(soilData);
    }

    @Override
    public SoilData getSoilDataFromApi(String polyId) {

        String url = UriComponentsBuilder.fromHttpUrl("http://api.agromonitoring.com/agro/1.0/soil")
                .queryParam("polyid", polyId)
                .queryParam("appid", apiKey)
                .toUriString();

        SoilApiResponse response = restTemplate.getForObject(url, SoilApiResponse.class);

        if (response != null) {
            try {
                return new SoilData(
                        polyId,
                        DateUtils.convertDateToString(Date.from(Instant.now())),
                        UnitUtils.convertKelvinToCelsius(response.getT10()),
                        response.getMoisture(),
                        UnitUtils.convertKelvinToCelsius(response.getT0()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
