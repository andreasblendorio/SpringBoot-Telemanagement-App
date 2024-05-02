package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MainData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer weatherDataId;

    private Double temp;

    private Double feelsLike;

    private Double tempMin;

    private Double tempMax;

    private Integer pressure;

    private Integer humidity;

    private Integer seaLevel;

    private Integer grndLevel;
}
