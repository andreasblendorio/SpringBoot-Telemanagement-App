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

    // CityEntity Fields: timezone, id, name, cod(?), dt, (country, base)
    // CityEntity Complex Objects: coord, weather, main, wind, (sys)

    @Id // this annotation will mark a column as a primary key
    @Column(name = "city_id")
    private Integer id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Coordinates coord; // maybe I should embed it

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL) // mapped by ?
    @JoinColumn(name = "weather_data_id")
    private List<Weather> weather;

    @Column(name = "Timezone")
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
    private MainData main;

    @Column(name = "visibility")
    private Integer visibility;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;

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

}