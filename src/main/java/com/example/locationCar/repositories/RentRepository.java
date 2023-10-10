package com.example.locationCar.repositories;

import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {
  Optional<RentModel> findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(
      VehicleModel vehicle, ZonedDateTime checkReturnDate, ZonedDateTime checkContractingDate);
}
