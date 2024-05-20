package com.dataservice.weatherDataService.entities;

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
    @OneToMany(cascade = CascadeType.ALL)
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
    private MainDataEntity main; // When the relationship is OneToMany, it's necessary to use List

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
}