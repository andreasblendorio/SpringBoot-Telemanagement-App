package com.dataservice.weatherDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
//@SpringBootApplication(scanBasePackages = {"com.example.demo.repos", "com.example.demo.entities"})
public class WeatherDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataServiceApplication.class, args);
	}
}
