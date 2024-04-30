package com.example.demo.repos;

import com.example.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

// Repo to map whatever json we're converting to the db entities
public interface CityRepo extends JpaRepository<City, Long> {
}
