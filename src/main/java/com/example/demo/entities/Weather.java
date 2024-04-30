package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Weather Table")
@Entity
public class Weather {

    // weather[id, main, description, icon]
    @Id // this annotation will mark a column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Long timezone;

    @Column
    private String main;

    @Column
    private String description;

    @Column
    private String icon;

    // Empty Constructor
    public Weather() {

    }

    // Constructor
    public Weather(Long timezone, String main, String description, String icon) {
        this.timezone = timezone;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    // Getters&Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

