package com.example.demo.repos;

import com.example.demo.dummies.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepo extends JpaRepository<DummyEntity, Long> {
}
