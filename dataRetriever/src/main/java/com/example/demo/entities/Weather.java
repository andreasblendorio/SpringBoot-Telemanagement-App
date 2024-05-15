package com.example.demo.entities;

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

    @Column(name = "weather_id")
    private Integer weatherId;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "icon") // this may be optional
    private String icon;
}

