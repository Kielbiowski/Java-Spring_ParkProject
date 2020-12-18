package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
}
