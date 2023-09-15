package com.example.locationCar.repositories;

import com.example.locationCar.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {

    @Query(value = "SELECT * FROM tb_address WHERE client_id = :idClient", nativeQuery = true)
    Optional<AddressModel> findByIdClient(UUID idClient);

    @Query(value = "SELECT * FROM tb_address WHERE employee_id = :idEmployee", nativeQuery = true)
    Optional<AddressModel> findByEmployeeId(UUID idEmployee);
}