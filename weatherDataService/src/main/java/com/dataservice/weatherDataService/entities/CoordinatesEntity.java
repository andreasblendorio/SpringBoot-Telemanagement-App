package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Sets up:
 * the CoordinatesEntity class declaration
 */

@Data
@Embeddable
public class CoordinatesEntity {

    @Column(name = "longitude")
    private Double lon;

    @Column(name = "latitude")
    private Double lat;
}
