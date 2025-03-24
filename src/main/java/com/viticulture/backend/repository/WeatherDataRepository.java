package com.viticulture.backend.repository;

import com.viticulture.backend.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, Long> {
}
