package com.example.locationCar.repositories;

<<<<<<< HEAD
import com.example.locationCar.models.LoginModel;
=======
import com.example.locationCar.models.EmployeeModel;
>>>>>>> develop
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/example/locationCar/repositories/EmployeeRepository.java
public interface EmployeeRepository extends JpaRepository<FuncionarioModel, UUID> {

=======
public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
>>>>>>> develop:src/main/java/com/example/locationCar/repositories/LoginRepository.java
=======
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {

    boolean existsByEmail(String email);

    boolean existsByCpfCnpj(String cpfCnpj);
>>>>>>> develop
}
