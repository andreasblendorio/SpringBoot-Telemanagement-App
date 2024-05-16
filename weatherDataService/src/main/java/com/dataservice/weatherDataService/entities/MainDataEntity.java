package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Table(name = "main_data_table")
@Entity
public class MainDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "weather_id")
    private Integer weatherDataId;

    @Column(name = "temperature")
    private Double temp;

    @Column(name = "feels_like")
    private Double feels_like;

    @Column(name = "minimum_temperature")
    private Double temp_min;

    @Column(name = "maximum_temperature")
    private Double temp_max;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "sea_level")
    private Integer sea_level;

    @Column(name = "ground_level")
    private Integer grnd_level;
}
