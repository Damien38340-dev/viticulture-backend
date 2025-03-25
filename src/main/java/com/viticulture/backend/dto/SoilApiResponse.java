package com.viticulture.backend.dto;

import lombok.Data;

@Data
public class SoilApiResponse {

    private long date;
    private double t10;
    private double moisture;
    private double t0;
}
