package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Sets up:
 * the CityEntity class declaration + the related "sub-entities",
 * the getTimeStamp method.
 * NOTE: Amount of boilerplate code can be significantly reduced by using the Lombok Getters&Setters
 */

@Data
@Table(name = "cities_table")
@Entity
public class CityEntity {

    // CityEntity Fields: base, visibility, dt, timezone, id, name, (cod)
    // CityEntity Complex Objects: coord, weather, main, wind, (cloud, sys)

    @Id // this annotation will mark a column as a primary key
    @Column(name = "city_id")
    private Integer id; // use the 'Integer' class (wrapper) instead of classic 'int' primitive to provide more flexibility

    @Getter
    @Setter
    @Embedded
    private CoordinatesEntity coord;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL) // mapped by ?
    private List<WeatherEntity> weather;

    @Column(name = "timezone")
    private Long timezone;

    @Column(name = "city_name")
    private String name;

    @Column(name = "country") // TODO: since this field is in a nested obj of the json, the obj mapper can't process it while in CityEntity, may need a workaround
    private String country;

    @Column(name = "base")
    private String base;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private MainDataEntity main;

    @Column(name = "visibility")
    private Integer visibility;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private WindEntity wind;

    @Column(name = "dt")
    private Integer dt;

    // getTimestamp method
    public LocalDateTime getTimestamp() {

        // Converts Unix timestamp to an instant
        Instant instant = Instant.ofEpochSecond(dt);

        // Obtains a LocalDateTime from the instant by means of an offset
        LocalDateTime timestamp = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return timestamp;
    }

    /*
    Classic development w/out the lombok pckg
    // Empty Constructor

    public CityEntity() {

    }

    /*
    // Constructor
    public CityEntity(Long timezone, String name, Long cod, String country, Long lon, Long lat) {
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
    */
}