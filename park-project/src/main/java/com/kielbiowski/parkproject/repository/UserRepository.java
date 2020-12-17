package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
