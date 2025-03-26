package com.viticulture.backend.repository;

import com.viticulture.backend.domainobject.WeatherData;
import com.viticulture.backend.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, Long> {

    Optional<WeatherDataEntity> findTopByCityOrderByDateDesc(String city);

}
