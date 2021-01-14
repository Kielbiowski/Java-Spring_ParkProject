package com.kielbiowski.parkproject.repository;

import com.kielbiowski.parkproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
