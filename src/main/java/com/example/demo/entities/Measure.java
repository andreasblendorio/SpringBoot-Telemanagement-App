package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Measure Table")
@Entity
public class Measure {

    // main[], visibility, dt
    @Id // this annotation will mark a column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Long main;

    @Column
    private Long visibility;

    @Column
    private String dt;

    // Empty Constructor
    public Measure() {

    }

    // Constructor
    public Measure(Long main, Long visibility, String dt) {
        this.main = main;
        this.visibility = visibility;
        this.dt = dt;
    }

    // Getters&Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getMain() {
        return main;
    }

    public void setMain(long main) {
        this.main = main;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }


}
