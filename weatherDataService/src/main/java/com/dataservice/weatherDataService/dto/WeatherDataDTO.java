package com.dataservice.weatherDataService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "Temperatures list cannot be empty")
    private List<@NotNull Double> temperatures;

    @NotEmpty(message = "Min temperatures list cannot be empty")
    private List<@NotNull Double> minTemperatures;

    @NotNull(message = "Average Minimum temperatures cannot be null")
    private Double averageMinTemperature;

    // @NotNull(message = "Average Minimum temperatures of last hour cannot be null")
    //private Double averageMinTemperatureLastHour;

    @NotEmpty(message = "Max temperatures list cannot be empty")
    private List<@NotNull Double> maxTemperatures;

    @NotNull(message = "Average Maximum temperature cannot be null")
    private Double averageMaxTemperature;

    // @NotNull(message = "Average Maximum temperatures of last hour cannot be null")
    //private Double averageMaxTemperatureLastHour;

    @NotEmpty(message = "Humidity list cannot be empty")
    private List<@NotNull Integer> humidity;
}
