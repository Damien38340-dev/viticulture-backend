package com.viticulture.backend.repository;

import com.viticulture.backend.entity.SoilDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoilDataRepository extends JpaRepository<SoilDataEntity, Long> {
    Optional<SoilDataEntity> findTopByPolyIdOrderByDateDesc(String polyId);
}
