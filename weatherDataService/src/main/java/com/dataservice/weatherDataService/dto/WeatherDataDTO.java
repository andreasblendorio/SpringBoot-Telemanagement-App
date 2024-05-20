package com.dataservice.weatherDataService.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Sets up:
 * the WeatherDataDTO class declaration, enabling the adoption of the DTO Design Pattern
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDataDTO {

    private List<Double> temperatures;

    private List<Double> minTemperatures;

    @Positive(message = "Average minimum temperature must be positive")
    private Double averageMinTemperature;

    //private Double averageMinTemperatureLastHour;

    private List<Double> maxTemperatures;

    @Positive(message = "Average minimum temperature must be positive")
    private Double averageMaxTemperature;

    //private Double averageMaxTemperatureLastHour;

    private List<Integer> humidity;
}
