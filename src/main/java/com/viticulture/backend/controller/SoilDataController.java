package com.viticulture.backend.controller;

import com.viticulture.backend.domainobject.SoilData;
import com.viticulture.backend.dto.SoilApiResponse;
import com.viticulture.backend.service.AgromonitoringApiService;
import com.viticulture.backend.service.SoilDataService;
import com.viticulture.backend.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soil")
public class SoilDataController {

    @Autowired
    private AgromonitoringApiService agromonitoringApiService;

    @Autowired
    private SoilDataService soilDataService;

    @GetMapping
    public ResponseEntity<List<SoilData>> getAllSoilData() {
        return ResponseEntity.ok(soilDataService.getAllSoilData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoilData> getSoilData(@PathVariable Long id) {
        return ResponseEntity.ok(soilDataService.getSoilDataById(id));
    }

    @PostMapping
    public ResponseEntity<SoilData> createSoilData(@RequestBody SoilApiResponse response) {
        SoilData soilData = new SoilData(
                response.getPolyId(),
                DateUtils.convertTimestampToString(response.getDate()),
                response.getT0(),
                response.getMoisture(),
                response.getT10()
        );
        return ResponseEntity.ok(soilDataService.saveSoilData(soilData));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SoilData> updateSoilData(@RequestBody SoilApiResponse response, @PathVariable Long id) {
        SoilData soilData = new SoilData(
                response.getPolyId(),
                DateUtils.convertTimestampToString(response.getDate()),
                response.getT0(),
                response.getMoisture(),
                response.getT10());

        return ResponseEntity.ok(soilDataService.updateSoilData(soilData, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSoilData(@PathVariable Long id) {
        soilDataService.deleteSoilData(id);
        return ResponseEntity.ok("Soil Data with ID : " + id + " deleted successfully");
    }

    @GetMapping("/polyid/{polyId}")
    public ResponseEntity<SoilData> getSoilDataByPolyId(@PathVariable String polyId) {
        return ResponseEntity.ok(agromonitoringApiService.getSoilData(polyId));
    }
}
