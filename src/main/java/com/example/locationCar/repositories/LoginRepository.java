package com.example.locationCar.repositories;

import com.example.locationCar.models.LoginModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
  Optional<LoginModel> findByEmail(String email);
}
