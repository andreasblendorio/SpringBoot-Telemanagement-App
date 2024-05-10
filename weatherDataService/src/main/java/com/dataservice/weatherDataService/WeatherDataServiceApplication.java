package com.dataservice.weatherDataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
//@SpringBootApplication(scanBasePackages = {"com.dataservice.weatherDataService.repos", "com.dataservice.weatherDataService.entities", "com.dataservice.weatherDataService.controller"})
public class WeatherDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataServiceApplication.class, args);
	}
}
