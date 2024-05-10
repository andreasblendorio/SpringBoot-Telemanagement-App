package com.dataservice.weatherDataService.repos;

//import com.example.demo.entities.WeatherDataEntity;
import com.dataservice.weatherDataService.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Repo to map whatever json we're converting to the corresponding db entities
public interface CityWeatherRepo extends JpaRepository<CityEntity, Long> {
}

