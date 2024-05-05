package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Weather Table")
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Integer weatherDataId;

    @Column(name = "WeatherId")
    private Integer weatherId;

    @Column(name = "Main")
    private String main;

    @Column(name = "Description")
    private String description;

    @Column(name = "Icon")
    private String icon;
}

