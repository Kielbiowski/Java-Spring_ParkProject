package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
