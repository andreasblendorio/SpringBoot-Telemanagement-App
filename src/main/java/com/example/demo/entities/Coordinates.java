package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Coordinates Table")
@Entity
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Longitude")
    private Double lon;

    @Column(name = "Latitude")
    private Double lat;
}
