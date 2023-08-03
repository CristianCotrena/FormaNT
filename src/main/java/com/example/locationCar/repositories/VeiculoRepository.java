package com.example.locationCar.repositories;

import com.example.locationCar.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<VehicleModel, UUID> {

}
