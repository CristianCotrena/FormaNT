package com.example.locationCar.repositories;

import com.example.locationCar.models.RentModel;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.locationCar.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {
    Optional<RentModel> findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(VehicleModel vehicle, ZonedDateTime checkReturnDate, ZonedDateTime checkContractingDate);

}
