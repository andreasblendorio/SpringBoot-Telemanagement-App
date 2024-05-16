package com.dataservice.weatherDataService.controller;

import com.dataservice.weatherDataService.repos.CityWeatherRepo;
import com.dataservice.weatherDataService.entities.CityEntity;
import com.dataservice.weatherDataService.entities.MainData;
import com.dataservice.weatherDataService.entities.Weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController // by using @RestController instead of vanilla @Controller and @ResponseBody, it will applied by default on all resources in that controller
@RequestMapping(path = "/api")
public class DataController {

    // Wiring the Repo
    private CityWeatherRepo cityWeatherRepo; // maybe can be set to "final" but this repo is constantly changing, so it may not be a good fit

    public DataController(CityWeatherRepo cityWeatherRepo) {
        this.cityWeatherRepo = cityWeatherRepo;
    }

    // Wiring the Entities
    private CityEntity cityEntity;
    private MainData main;
    private Weather weather;

    // Root Endpoint
    @GetMapping("/")
    public String index() {
        return "start";
    }

    // GET Endpoint(s)
    @GetMapping(path = "/hello") // endpoint not compliant with the naming convention
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello() {
        return "Hello from the Controller";
    }

    @GetMapping("/temperature")
    public List<Double> getTemperatureData() {
        // Access temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp()) // Extract temperature from MainData
                .collect(Collectors.toList());
    }

    @GetMapping("/min-temperature")
    public List<Double> getMinTemperatureData() {
        // Access minimum temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp_min()) // Extract minimum temperature from MainData
                .collect(Collectors.toList());
    }

    @GetMapping("/avg-min-temperature")
    public Double getAverageMinTemperature() {
        // Access avg minimum temperature data via CityEntity
        List<Double> minTemperatures = getMinTemperatureData();

        // Calculate the average of minimum temperatures
        if (!minTemperatures.isEmpty()) {
            double averageMinTemperature = minTemperatures.stream()
                    .mapToDouble(value -> value)
                    .average()
                    .getAsDouble();
            return averageMinTemperature;
        } else {
            return null; // Return null if there is no data
        }
    }

    @GetMapping("/avg-min-temperature-last-hour")
    public Double getAverageMinTemperatureLastHour() {
        // Current hour
        LocalDateTime currentTime = LocalDateTime.now();

        // Going back 60 mins
        LocalDateTime oneHourAgo = currentTime.minusHours(1);

        // Filtering entities to obtain the one with a timestamp from the last hour
        List<Double> minTemperaturesLastHour = cityWeatherRepo.findAll().stream()
                .filter(cityEntity -> {
                    // Comparing the entity timestamp with the time of one hour ago
                    LocalDateTime entityTimestamp = cityEntity.getTimestamp();
                    return entityTimestamp.isAfter(oneHourAgo) && entityTimestamp.isBefore(currentTime);
                })
                .map(cityEntity -> cityEntity.getMain().getTemp_min()) // Retrieving the minimum temperature
                .collect(Collectors.toList());

        // Calculating the min_temp avg
        if (!minTemperaturesLastHour.isEmpty()) {
            double averageMinTemperatureLastHour = minTemperaturesLastHour.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .getAsDouble();
            return averageMinTemperatureLastHour;
        } else {
            return null; // Returning null if there's no data
        }
    }

    @GetMapping("/max-temperature")
    public List<Double> getMaxTemperatureData() {
        // Access maximum temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp_max()) // Extract maximum temperature from MainData
                .collect(Collectors.toList());
    }

    @GetMapping("/avg-max-temperature")
    public Double getAverageMaxTemperature() {
        // Access avg maximum temperature data via CityEntity
        List<Double> maxTemperatures = getMaxTemperatureData();

        // Calculate the average of maximum temperatures
        if (!maxTemperatures.isEmpty()) {
            double averageMaxTemperature = maxTemperatures.stream()
                    .mapToDouble(value -> value)
                    .average()
                    .getAsDouble();
            return averageMaxTemperature;
        } else {
            return null; // Return null if there is no data
        }
    }

    @GetMapping("/avg-max-temperature-last-hour")
    public Double getAverageMaxTemperatureLastHour() {
        // Current hour
        LocalDateTime currentTime = LocalDateTime.now();

        // Going back 60 mins
        LocalDateTime oneHourAgo = currentTime.minusHours(1);

        // Filtering entities to obtain the one with a timestamp from the last hour
        List<Double> minTemperaturesLastHour = cityWeatherRepo.findAll().stream()
                .filter(cityEntity -> {
                    // Comparing the entity timestamp with the time of one hour ago
                    LocalDateTime entityTimestamp = cityEntity.getTimestamp();
                    return entityTimestamp.isAfter(oneHourAgo) && entityTimestamp.isBefore(currentTime);
                })
                .map(cityEntity -> cityEntity.getMain().getTemp_max()) // Retrieving the minimum temperature
                .collect(Collectors.toList());

        // Calculating the min_temp avg
        if (!minTemperaturesLastHour.isEmpty()) {
            double averageMinTemperatureLastHour = minTemperaturesLastHour.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .getAsDouble();
            return getAverageMaxTemperatureLastHour();
        } else {
            return null; // Returning null if there's no data
        }
    }

    @GetMapping("/humidity")
    public List<Integer> getHumidityData() {
        // Access humidity data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getHumidity()) // Extract humidity from MainData
                .toList();
    }

    // POST Endpoint(s)
    @PostMapping("/post")
    public String post(@RequestBody String message) {
        return "Request Accepted and message is : " + message;
        // we can also pass an obj rather than a string, as long as it will be defined in another Class file
    }

    @PostMapping("/post-city")
    public String post(@RequestBody DummyCity dummyCity) {
        return "Request Accepted and message is : " + dummyCity.toString();
        // We can also pass an obj rather than a string, as long as it will be defined in another Class file
    }

}
