package com.example.locationCar.repositories;

import com.example.locationCar.models.VehicleModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository
    extends JpaRepository<VehicleModel, UUID>, JpaSpecificationExecutor<VehicleModel> {
  Optional<VehicleModel> findByLicense(String license);

  Optional<Boolean> existsByLicense(String license);

  void deleteByLicense(String license);

  Optional<VehicleModel> findByIdVehicleAndStatus(UUID idVehicle, Integer status);
}
