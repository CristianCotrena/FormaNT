package com.example.locationCar.repositories;

import com.example.locationCar.models.AddressModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {}
