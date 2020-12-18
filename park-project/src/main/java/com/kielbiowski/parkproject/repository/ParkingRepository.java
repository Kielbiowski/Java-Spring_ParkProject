package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
}
