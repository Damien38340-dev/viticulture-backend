package com.viticulture.backend.controller;

import com.viticulture.backend.domainobject.SoilMoistureData;
import com.viticulture.backend.service.AgromonitoringApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/soil")
public class SoilDataController {

    @Autowired
    private AgromonitoringApiService agromonitoringApiService;

    @GetMapping("/{polyId}")
    public ResponseEntity<SoilMoistureData> getSoilMoistureData(@PathVariable String polyId) {
        return ResponseEntity.ok(agromonitoringApiService.getSoilMoistureData(polyId));
    }


}
