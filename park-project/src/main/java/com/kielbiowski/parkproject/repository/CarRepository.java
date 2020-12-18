package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
