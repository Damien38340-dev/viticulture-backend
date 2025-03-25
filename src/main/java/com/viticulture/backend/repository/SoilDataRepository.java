package com.viticulture.backend.repository;

import com.viticulture.backend.entity.SoilMoistureDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilDataRepository extends JpaRepository<SoilMoistureDataEntity, Long> {
}
