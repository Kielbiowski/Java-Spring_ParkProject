package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
