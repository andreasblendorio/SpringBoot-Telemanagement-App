package com.example.demo.repos;

import com.example.demo.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sets up:
 * the Repository where to store data
 */

// Repo to map whatever json we're converting to the corresponding db entities
public interface CityWeatherRepo extends JpaRepository<CityEntity, Long> {
}

