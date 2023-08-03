package com.example.locationCar.repositories;

import com.example.locationCar.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
<<<<<<< HEAD:src/main/java/com/example/locationCar/repositories/EmployeeRepository.java
public interface EmployeeRepository extends JpaRepository<FuncionarioModel, UUID> {

=======
public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
>>>>>>> develop:src/main/java/com/example/locationCar/repositories/LoginRepository.java
}
