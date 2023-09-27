package com.example.locationCar.repositories;

import com.example.locationCar.models.RentModel;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {

    Page<RentModel> findByClientIdAndEmployeeId (UUID idClient, UUID idEmployee, Pageable pageable);

    Page<RentModel> findByClientId(UUID clientId, Pageable pageable);

    Page<RentModel> findByEmployeeId(UUID employeeId, Pageable pageable);


}
