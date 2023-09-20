package com.example.locationCar.repositories;

import com.example.locationCar.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, UUID>, JpaSpecificationExecutor<VehicleModel> {
    Optional<VehicleModel> findByLicense(String license);

<<<<<<< HEAD
    Optional<Boolean> existsByLicense (String license);

    void deleteByLicense (String license);
=======

>>>>>>> f75291f4721f7c0320b29f062c04baf6916e2e1a
}
