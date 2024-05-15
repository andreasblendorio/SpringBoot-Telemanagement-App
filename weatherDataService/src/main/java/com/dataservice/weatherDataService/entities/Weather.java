package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "weather_table")
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Integer weatherDataId;

    @Column(name = "weatherId")
    private Integer weatherId;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;
}
