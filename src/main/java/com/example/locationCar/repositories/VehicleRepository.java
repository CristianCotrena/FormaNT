package com.example.locationCar.repositories;

import com.example.locationCar.models.VehicleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository
    extends JpaRepository<VehicleModel, UUID>, JpaSpecificationExecutor<VehicleModel> {
  Optional<VehicleModel> findByLicense(String license);

  Optional<Boolean> existsByLicense(String license);

  void deleteByLicense(String license);

  Optional<VehicleModel> findByIdVehicleAndStatus(UUID idVehicle, Integer status);

  @Query("SELECT v FROM VehicleModel v WHERE v.idVehicle NOT IN :vehicleIds")
  List<VehicleModel> findAvailableVehicles(@Param("vehicleIds") List<String> vehicleIds, Pageable pageable);

  @Query("SELECT v FROM VehicleModel v WHERE v.idVehicle IN :vehicleIds")
  List<VehicleModel> findRentedVehicles(@Param("vehicleIds") List<String> vehicleIds, Pageable pageable);
}
