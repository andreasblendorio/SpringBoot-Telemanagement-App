package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "coordinates_table")
@Entity
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "longitude")
    private Double lon;

    @Column(name = "latitude")
    private Double lat;
}
