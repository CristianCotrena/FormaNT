package com.example.locationCar.repositories;

import com.example.locationCar.models.RentModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {}
