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
    private Double feels_like;

    @Column(name = "Minimum Temperature")
    private Double temp_min;

    @Column(name = "Maximum Temperature")
    private Double temp_max;

    @Column(name = "Pressure")
    private Integer pressure;

    @Column(name = "Humidity")
    private Integer humidity;

    @Column(name = "Sea Level")
    private Integer sea_evel;

    @Column(name = "Ground Level")
    private Integer grnd_level;
}
