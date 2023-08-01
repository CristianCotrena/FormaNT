package com.example.locationCar.repositories;

import com.example.locationCar.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<EmployeeModel, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpfCnpj(String cpfCnpj);
}
