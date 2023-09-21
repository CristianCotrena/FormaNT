package com.example.locationCar.repositories;

import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {

  Optional<AddressModel> findByIdClient(ClientModel clientModel);

  Optional<AddressModel> findByIdEmployee(EmployeeModel employeeModel);

  Optional<AddressModel> findByIdClientIdClient(UUID idClient);

  Optional<AddressModel> findByIdEmployeeEmployeeId(UUID idEmployee);
}
