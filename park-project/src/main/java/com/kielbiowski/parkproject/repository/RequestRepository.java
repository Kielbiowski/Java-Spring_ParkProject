package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
