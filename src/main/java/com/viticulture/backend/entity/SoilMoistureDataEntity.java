package com.viticulture.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SoilMoistureDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date; // Time of data calculation (unix time, UTC time zone)
    private double t10; // Temperature on the 10 centimeters depth, Kelvins
    private double moisture;  // Soil moisture value in m3/m3
    private double t0; // Surface temperature, Kelvins

    public SoilMoistureDataEntity(String date, double t10, double moisture, double t0) {
        this.date = date;
        this.t10 = t10;
        this.moisture = moisture;
        this.t0 = t0;
    }

    public SoilMoistureDataEntity() {}
}
