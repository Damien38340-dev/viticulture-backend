package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.SoilMoistureData;

public interface AgromonitoringApiService {

    SoilMoistureData getSoilMoistureData(String polyId);
}
