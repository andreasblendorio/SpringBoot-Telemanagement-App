package com.example.demo.dummies;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class DummyHobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hobby;
}