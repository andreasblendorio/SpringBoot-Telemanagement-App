package com.dataservice.weatherDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sets up:
 * the main SpringBoot App entry point to start the application from the get-go
 */

// App Entry Point
@SpringBootApplication()
public class WeatherDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataServiceApplication.class, args);
	}
}
