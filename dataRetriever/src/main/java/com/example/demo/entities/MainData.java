package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Main Data Table")
@Entity
public class MainData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Weather Identifier")
    private Integer weatherDataId;

    @Column(name = "Temperature")
    private Double temp;

    @Column(name = "Feels Like")
    private Double feelsLike;

    @Column(name = "Minimum Temperature")
    private Double tempMin;

    @Column(name = "Maximum Temperature")
    private Double tempMax;

    @Column(name = "Pressure")
    private Integer pressure;

    @Column(name = "Humidity")
    private Integer humidity;

    @Column(name = "Sea Level")
    private Integer seaLevel;

    @Column(name = "Ground Level")
    private Integer grndLevel;
}
