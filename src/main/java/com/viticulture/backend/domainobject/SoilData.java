package com.viticulture.backend.domainobject;

import lombok.Data;

@Data
public class SoilData {

    private String polyId;
    private String date; // Time of data calculation (unix time, UTC time zone)
    private double t10; // Temperature on the 10 centimeters depth, converted from Kelvin to Celsius
    private double moisture;  // Soil moisture value in m3/m3
    private double t0; // Surface temperature, converted from Kelvin to Celsius

    public SoilData(String polyId, String date, double t10, double moisture, double t0) {
        this.polyId = polyId;
        this.date = date;
        this.t10 = Math.round(t10 * 10.0) / 10.0; // Round to 1 decimal
        this.moisture = moisture;
        this.t0 = Math.round(t0  * 10.0) / 10.0;
    }

    public SoilData() {

    }
}
