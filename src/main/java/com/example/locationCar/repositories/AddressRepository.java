package com.example.locationCar.repositories;

import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    Optional<AddressModel> findByIdClient(ClientModel clientModel);
    Optional<AddressModel> findByIdEmployee(EmployeeModel employeeModel);

}
