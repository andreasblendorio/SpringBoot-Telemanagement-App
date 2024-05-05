package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Wind Table")
@Entity
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Speed")
    private Double speed;

    @Column(name = "Deg")
    private Integer deg;

    @Column(name = "Gust")
    private Double gust;

}
