package com.dataservice.weatherDataService.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Sets up:
 * the WindEntity class declaration
 */

@Data
@Table(name = "wind_table")
@Entity
public class WindEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "deg")
    private Integer deg;

    @Column(name = "gust")
    private Double gust;
}
