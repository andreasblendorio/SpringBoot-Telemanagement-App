package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Cities Table")
@Entity
public class City {

    /**
     * NOTE: Amount of boiler-plate code can be significantly reduced by using the Lombok Getters&Setters
     */


    // id, timezone, name, cod, country, lon, lat
    @Id // this annotation will mark a column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Long timezone;

    @Column
    private String name;

    @Column
    private Long cod;

    @Column
    private String country;

    @Column
    private Long lon;

    @Column
    private Long lat;

    // Empty Constructor
    public City() {

    }

    // Constructor
    public City(Long timezone, String name, Long cod, String country, Long lon, Long lat) {
        this.timezone = timezone;
        this.name = name;
        this.cod = cod;
        this.country = country;
        this.lon = lon;
        this.lat = lat;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLon() {
        return lon;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLat() {
        return lat;
    }


}