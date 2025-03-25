package com.viticulture.backend.service;

import com.viticulture.backend.domainobject.SoilData;
import com.viticulture.backend.entity.SoilDataEntity;
import com.viticulture.backend.repository.SoilDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoilDataServiceImpl implements SoilDataService {

    @Autowired
    private SoilDataRepository soilDataRepository;

    @Override
    public List<SoilData> getAllSoilData() {
        List<SoilDataEntity> soilDataEntities = soilDataRepository.findAll();
        return soilDataEntities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public SoilData getSoilDataById(Long id) {
        SoilDataEntity soilDataEntity = soilDataRepository.getReferenceById(id);
        return convertToDomain(soilDataEntity);
    }

    @Override
    public SoilData saveSoilData(SoilData soilData) {
        SoilDataEntity soilDataEntity = convertToEntity(soilData);

        soilDataRepository.save(soilDataEntity);

        return soilData;
    }

    @Override
    public SoilData updateSoilData(SoilData soilData, Long id) {
        SoilDataEntity soilDataEntity = soilDataRepository.getReferenceById(id);

        soilDataEntity.setDate(soilData.getDate());
        soilDataEntity.setT0(soilData.getT0());
        soilDataEntity.setMoisture(soilData.getMoisture());
        soilDataEntity.setT10(soilData.getT10());

        soilDataRepository.save(soilDataEntity);

        return soilData;
    }

    @Override
    public void deleteSoilData(Long id) {
        soilDataRepository.deleteById(id);
    }

    private SoilData convertToDomain(SoilDataEntity soilDataEntity) {
        return new SoilData(
                soilDataEntity.getDate(),
                soilDataEntity.getT0(),
                soilDataEntity.getMoisture(),
                soilDataEntity.getT10()
        );
    }

    private SoilDataEntity convertToEntity(SoilData soilData) {
        return new SoilDataEntity(
                soilData.getDate(),
                soilData.getT0(),
                soilData.getMoisture(),
                soilData.getT10()
        );
    }
}
