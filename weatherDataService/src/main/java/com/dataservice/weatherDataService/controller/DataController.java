package com.dataservice.weatherDataService.controller;

import com.dataservice.weatherDataService.entities.DummyCity;
import com.dataservice.weatherDataService.repos.CityWeatherRepo;
import com.dataservice.weatherDataService.entities.CityEntity;
import com.dataservice.weatherDataService.entities.MainData;
import com.dataservice.weatherDataService.entities.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/data")
public class DataController {

    // Wiring the Repo
    @Autowired
    private CityWeatherRepo cityWeatherRepo;

    // Wiring the Entities
    @Autowired
    private CityEntity cityEntity;

    @Autowired
    private MainData main;

    //jackson
    @Autowired
    private Weather weather;


    @GetMapping("/")
    public String index() {
        return "start";
    }

    // GET Endpoint
    @GetMapping(path = "/hello") // endpoint not compliant with the naming convention
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello() {
        return "Hello from the Controller";
    }
    // @RestController instead of vanilla @Controller and @ResponseBody by default is applied on all resources in that controller.

    @GetMapping("/temperature")
    public List<Double> getTemperatureData() {
        // Access temperature data through CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(CityEntity::getMain)  // Get WeatherData object
                .map(MainData::getTemp)    // Extract temperature from MainData
                .toList();
    }

    @GetMapping("/humidity")
    public List<Integer> getHumidityData() {
        // Access humidity data through CityEntity
        return cityWeatherRepo.findAll().stream()
                .map(CityEntity::getMain)    // Get WeatherData object
                .map(MainData::getHumidity)  // Extract humidity from MainData
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
        // we can also pass an obj rather than a string, as long as it will be defined in another Class file
    }


}
