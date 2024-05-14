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
@Table(name = "Cities Table")
@Entity
public class CityEntity {

    /**
     * NOTE: Amount of boiler-plate code can be significantly reduced by using the Lombok Getters&Setters
     * Adding @NoArgsConstructor ny Lombok may help when dealing with xml
     */

    // id, coord, timezone, name, country, base
    @Id // this annotation will mark a column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "City name")
    private String name;

    @Column(name = "Country")
    private String country;

    @Column(name = "Base")
    private String base;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private MainData main;

    @Column(name = "Visibility")
    private Integer visibility;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;

    @Column(name = "Dt")
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