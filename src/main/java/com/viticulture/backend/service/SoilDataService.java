package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.SoilData;

import java.util.List;

public interface SoilDataService {

    List<SoilData> getAllSoilData();

    SoilData getSoilDataById(Long id);

    SoilData saveSoilData(SoilData soilData);

    SoilData updateSoilData(SoilData soilData, Long id);

    void deleteSoilData(Long id);
}
