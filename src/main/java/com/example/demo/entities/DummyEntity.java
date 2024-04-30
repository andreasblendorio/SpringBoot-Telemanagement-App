package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class DummyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private DummyAddress address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DummyHobby> hobbies;


}
