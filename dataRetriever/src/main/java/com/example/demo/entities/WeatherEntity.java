package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Sets up:
 * the WeatherEntity class declaration
 */

@Data
@Table(name = "weather_table")
@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Integer id;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "icon") // this may be optional
    private String icon;
}

