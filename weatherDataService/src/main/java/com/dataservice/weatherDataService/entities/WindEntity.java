package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "wind_table")
@Entity
public class WindEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "deg")
    private Integer deg;

    @Column(name = "gust")
    private Double gust;
}
