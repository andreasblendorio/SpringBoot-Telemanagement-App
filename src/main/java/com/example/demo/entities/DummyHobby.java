package com.example.demo.entities;

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