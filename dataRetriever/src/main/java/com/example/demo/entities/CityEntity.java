package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Data
@Table(name = "cities_table")
@Entity
public class CityEntity {

    /**
     * Setting up:
     * the CityEntity class declaration + the related "sub-entities"
     * the getTimeStamp method
     * NOTE: Amount of boiler-plate code can be significantly reduced by using the Lombok Getters&Setters
     */

    // CityEntity Fields: timezone, id, name, cod(?), dt, (country, base)
    // CityEntity Complex Objects: coord, weather, main, wind, (sys)

    @Id // this annotation will mark a column as a primary key
    @Column(name = "city_id")
    private Integer id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private CoordinatesEntity coord; // maybe I should embed it

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL) // mapped by ?
    @JoinColumn(name = "weather_data_id")
    private List<WeatherEntity> weather;

    @Column(name = "timezone")
    private Long timezone;

    @Column(name = "city_name") // making it unique will drag the microservice in failure: "ERRORE: un valore chiave duplicato viola il vincolo univoco cities_table_city_name_key"
    private String name;

    @Column(name = "country")
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
    private int dt;

    // getTimestamp method
    public LocalDateTime getTimestamp() {

        // Converting Unix timestamp to an instant
        Instant instant = Instant.ofEpochSecond(dt);

        // Obtain a LocalDateTime from the instant by means of an offset
        LocalDateTime timestamp = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return timestamp;
    }

    /*
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

//created timestamp
}