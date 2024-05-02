package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class WeatherDataEntity {

    @Id // this annotation will mark a column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_data_id")
    private List<Weather> weather;


    @OneToOne(cascade = CascadeType.ALL)
    private MainData main;

    private Integer visibility;

    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;

    @OneToOne(cascade = CascadeType.ALL)
    private Clouds clouds;

    private Long dt;

    @OneToOne(cascade = CascadeType.ALL)
    private Sys sys;

    private Integer timezone;

    private Long cityId;

    private String cityName;

    private Integer cod;
}
