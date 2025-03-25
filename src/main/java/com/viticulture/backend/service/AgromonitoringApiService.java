package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.SoilData;

public interface AgromonitoringApiService {

    SoilData getSoilData(String polyId);
}
