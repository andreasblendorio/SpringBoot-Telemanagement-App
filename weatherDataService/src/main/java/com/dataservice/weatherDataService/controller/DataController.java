package com.dataservice.weatherDataService.controller;

import com.dataservice.weatherDataService.dto.WeatherDataDTO;
import com.dataservice.weatherDataService.repos.CityWeatherRepo;
import com.dataservice.weatherDataService.entities.CityEntity;
import com.dataservice.weatherDataService.entities.MainDataEntity;
import com.dataservice.weatherDataService.entities.WeatherEntity;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sets up:
 * the RestController layers' logic, exposing the main endpoints:
 * the /api path,
 * the / root endpoint,
 * the /hello welcoming endpoint,
 * the /temperatures endpoint,
 * the /min-temperatures endpoint,
 * the /avg-min-temperatures endpoint,
 * the /avg-min-temperatures-last-hour endpoint,
 * the /max-temperatures endpoint,
 * the /avg-max-temperatures endpoint,
 * the /avg-max-temperatures-last-hour endpoint,
 * the /humidity endpoint,
 * the /download-weather-summary returning the responses in a .xml file
 * As long as the corresponding methods: getTemperatureData, getMinTemperatureData, getAverageMinTemperatureData, getAverageMinTemperatureLastHour, getMaxTemperatureData, getAverageMaxTemperatureData, getAverageMaxTemperatureLastHour, getHumidityData, getWeatherDataSummary.
 */

@RestController // by using @RestController instead of vanilla @Controller and @ResponseBody, it will be applied by default on all resources in that controller
@RequestMapping(path = "/api")
public class DataController {

    // Wires the Repo
    private CityWeatherRepo cityWeatherRepo; // maybe can be set to "final" but this repo is constantly changing, so it may not be a good fit

    // Sets Constructor
    public DataController(CityWeatherRepo cityWeatherRepo) {
        this.cityWeatherRepo = cityWeatherRepo;
    }

    // Wires the Entities
    private CityEntity cityEntity;
    private MainDataEntity main;
    private WeatherEntity weather;

    // GET Endpoint(s)

    // Root Endpoint
    @GetMapping("/")
    public String index() {
        return "start";
    }

    // Welcome
    @GetMapping(path = "/hello") // endpoint not compliant with the naming convention
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello() {
        return "Hello from the Controller";
    }

    // Temperatures
    @GetMapping("/temperatures") // resources always in plural
    public List<Double> getTemperatureData() {
        // Accesses temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp()) // Extract temperature from MainData
                .collect(Collectors.toList());
    }

    // Min Temperatures
    @GetMapping("/min-temperatures")
    public List<Double> getMinTemperatureData() {
        // Accesses minimum temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp_min()) // Extract minimum temperature from MainData
                .collect(Collectors.toList());
    }

    // Avg Min Temperatures
    @GetMapping("/avg-min-temperatures")
    public Double getAverageMinTemperature() {
        // Accesses avg minimum temperature data via CityEntity
        List<Double> minTemperatures = getMinTemperatureData();

        // Calculates the average of minimum temperatures
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

    // Avg Min Temperatures (last hour)
    @GetMapping("/avg-min-temperatures-last-hour")
    public Double getAverageMinTemperatureLastHour() {
        // Current hour
        LocalDateTime currentTime = LocalDateTime.now();

        // Goes back 60 mins
        LocalDateTime oneHourAgo = currentTime.minusHours(1);

        // Filters entities to obtain the one with a timestamp from the last hour
        List<Double> minTemperaturesLastHour = cityWeatherRepo.findAll().stream()
                .filter(cityEntity -> {
                    // Comparing the entity timestamp with the time of one hour ago
                    LocalDateTime entityTimestamp = cityEntity.getTimestamp();
                    return entityTimestamp.isAfter(oneHourAgo) && entityTimestamp.isBefore(currentTime);
                })
                .map(cityEntity -> cityEntity.getMain().getTemp_min()) // Retrieves the minimum temperature
                .toList();

        // Calculates the min_temp avg
        if (!minTemperaturesLastHour.isEmpty()) {
            double averageMinTemperatureLastHour = minTemperaturesLastHour.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .getAsDouble();
            return averageMinTemperatureLastHour;
        } else {
            return null; // Returns null if there is no data
        }
    }

    // Max Temperatures
    @GetMapping("/max-temperatures")
    public List<Double> getMaxTemperatureData() {
        // Accesses maximum temperature data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getTemp_max()) // Extracts maximum temperature from MainData
                .collect(Collectors.toList());
    }

    // Avg Max Temperatures
    @GetMapping("/avg-max-temperatures")
    public Double getAverageMaxTemperature() {
        // Accesses avg maximum temperature data via CityEntity
        List<Double> maxTemperatures = getMaxTemperatureData();

        // Calculates the average of maximum temperatures
        if (!maxTemperatures.isEmpty()) {
            double averageMaxTemperature = maxTemperatures.stream()
                    .mapToDouble(value -> value)
                    .average()
                    .getAsDouble();
            return averageMaxTemperature;
        } else {
            return null; // Returns null if there is no data
        }
    }

    // Avg Max Temperatures (last hour)
    @GetMapping("/avg-max-temperatures-last-hour")
    public Double getAverageMaxTemperatureLastHour() {
        // Current hour
        LocalDateTime currentTime = LocalDateTime.now();

        // Goes back 60 mins
        LocalDateTime oneHourAgo = currentTime.minusHours(1);

        // Filters entities to obtain the one with a timestamp from the last hour
        List<Double> minTemperaturesLastHour = cityWeatherRepo.findAll().stream()
                .filter(cityEntity -> {
                    // Compares the entity timestamp with the time of one hour ago
                    LocalDateTime entityTimestamp = cityEntity.getTimestamp();
                    return entityTimestamp.isAfter(oneHourAgo) && entityTimestamp.isBefore(currentTime);
                })
                .map(cityEntity -> cityEntity.getMain().getTemp_max()) // Retrieves the minimum temperature
                .toList();

        // Calculates the min_temp avg
        if (!minTemperaturesLastHour.isEmpty()) {
            double averageMinTemperatureLastHour = minTemperaturesLastHour.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElseThrow(IllegalStateException::new);
            return getAverageMaxTemperatureLastHour();
        } else {
            return null; // Returns null if there's no data
        }
    }

    // Humidity
    @GetMapping("/humidity")
    public List<Integer> getHumidityData() {
        // Accesses humidity data via CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(cityEntity -> cityEntity.getMain().getHumidity()) // Extract humidity from MainData
                .toList();
    }

    // Summary
    @GetMapping(value = "/download-weather-summary")
    public ResponseEntity<String> downloadWeatherSummary() throws IOException {
        // Gathers data
        List<Double> temperatures = getTemperatureData();
        List<Double> minTemperatures = getMinTemperatureData();
        List<Double> maxTemperatures = getMaxTemperatureData();
        Double avgMinTemperature = getAverageMinTemperature();
        Double avgMaxTemperature = getAverageMaxTemperature();
        List<Integer> humidity = getHumidityData();

        // Populates the DTO
        WeatherDataDTO summary = getWeatherDataSummary();

        // Converts summary obj (DTO) to XML
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(summary);

        // Sets headers + response
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather-summary.xml");
        headers.setContentType(MediaType.APPLICATION_XML);

        return ResponseEntity.ok()
                .headers(headers)
                .body(xml);
    }

    // ext method
    private WeatherDataDTO getWeatherDataSummary() {
        WeatherDataDTO summary = new WeatherDataDTO();

        summary.setTemperatures(getTemperatureData());
        summary.setMinTemperatures(getMinTemperatureData());
        summary.setAverageMinTemperature(getAverageMinTemperature());
        //summary.setAverageMinTemperatureLastHour(getAverageMinTemperatureLastHour());
        summary.setMaxTemperatures(getMaxTemperatureData());
        summary.setAverageMaxTemperature(getAverageMaxTemperature());
        //summary.setAverageMaxTemperatureLastHour(getAverageMaxTemperatureLastHour());
        summary.setHumidity(getHumidityData());
        return summary;
    }

    /*
    // POST Endpoint(s)
    @PostMapping("/post")
    public String post(@RequestBody String message) {
        return "Request Accepted and message is : " + message;
        // we can also pass an obj rather than a string, as long as it will be defined in another Class file
    }

    @PostMapping("/post-cities")
    public String post(@RequestBody DummyCity dummyCity) {
        return "Request Accepted and message is : " + dummyCity.toString();
        // We can also pass an obj rather than a string, as long as it will be defined in another Class file
    }
    */
}
