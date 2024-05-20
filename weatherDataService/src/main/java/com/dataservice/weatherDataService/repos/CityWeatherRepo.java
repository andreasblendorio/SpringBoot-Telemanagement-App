package com.dataservice.weatherDataService.repos;

import com.dataservice.weatherDataService.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sets up:
 * the Repository where to store data
 */

// Repo to map whatever json we're converting to the corresponding db entities
public interface CityWeatherRepo extends JpaRepository<CityEntity, Long> {
}

