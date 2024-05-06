package com.dataservice.weatherDataService;

import com.example.demo.entities.CityEntity;
import com.example.demo.entities.MainData;
import com.example.demo.entities.Weather;
import com.example.demo.repos.CityWeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    // Wiring the Repo
    @Autowired
    private CityWeatherRepo cityWeatherRepo;

    // Wiring the Entities
    @Autowired
    private CityEntity cityEntity;

    @Autowired
    private MainData main;

    @Autowired
    private Weather weather;

    // GET Endpoint
    @GetMapping("/hello") // endpoint not compliant with the naming convention
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello() {
        return "Hello from the Controller";
    }

    // POST Endpoint(s)
    @PostMapping("/post")
    public String post(@RequestBody String message) {
        return "Request Accepted and message is : " + message;
        // we can also pass an obj rather than a string, as long as it will be defined in another Class file
    }
    @PostMapping("/post-city")
    public String post(@RequestBody City city) {
        return "Request Accepted and message is : " + city.toString();
        // we can also pass an obj rather than a string, as long as it will be defined in another Class file
    }
}
