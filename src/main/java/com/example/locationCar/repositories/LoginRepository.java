package com.example.locationCar.repositories;

import com.example.locationCar.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
}
