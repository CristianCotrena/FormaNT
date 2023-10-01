package com.example.locationCar.repositories;

import com.example.locationCar.models.RentModel;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {

    Page<RentModel> findByIdClientAndEmployeeId(UUID idClient, UUID employeeId, Pageable pageable);

    Page<RentModel> findByIdClient(UUID idClient, Pageable pageable);

    Page<RentModel> findByEmployeeId(UUID employeeId, Pageable pageable);


}
