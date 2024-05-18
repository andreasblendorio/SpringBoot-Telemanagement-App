package com.dataservice.weatherDataService.entities;

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

    // CityEntity Fields: base, visibility, dt, timezone, id, name, (cod)
    // CityEntity Complex Objects: coord, weather, main, wind, (cloud, sys)

    @Id // this annotation will mark a column as a primary key
    @Column(name = "city_id")
    private Integer id; // using the 'Integer' class (wrapper) instead of classic 'int' primitive to provide more flexibility

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

    @Column(name = "country") // since this field is in a nested obj of the json, the obj mapper can't process it while in CityEntity, may need a workaround
    private String country;

    @Column(name = "base")
    private String base;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private MainDataEntity main; // When the relationship is OneToMany, it's necessary to use List

    @Column(name = "visibility")
    private Integer visibility;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)  // key: city_id, dt --> join to city_id
    private WindEntity wind;

    @Column(name = "dt")
    private Integer dt;

    // getTimestamp method
    public LocalDateTime getTimestamp() {
        // Converting Unix timestamp to an instant
        Instant instant = Instant.ofEpochSecond(dt);

        // Obtain a LocalDateTime from the instant by means of an offset
        LocalDateTime timestamp = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return timestamp;
    }

}