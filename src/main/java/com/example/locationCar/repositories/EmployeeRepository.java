package com.example.locationCar.repositories;

import com.example.locationCar.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpfCnpj(String cpfCnpj);
    EmployeeModel findByCpfCnpj(String cpfCnpj);
    EmployeeModel findByEmail(String email);
}
