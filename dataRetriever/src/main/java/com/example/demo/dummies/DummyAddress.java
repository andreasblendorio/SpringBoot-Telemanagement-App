package com.example.demo.dummies;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class DummyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

}
