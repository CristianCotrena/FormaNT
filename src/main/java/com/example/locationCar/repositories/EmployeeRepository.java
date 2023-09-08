package com.example.locationCar.repositories;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {

  boolean existsByEmail(String email);

  boolean existsByCpfCnpj(String cpfCnpj);

  EmployeeModel findByCpfCnpj(String cpfCnpj);

  EmployeeModel findByEmail(String email);

  @Query(
      "SELECT c FROM EmployeeModel c WHERE (:role IS NULL OR c.role = :role) AND (:position IS NULL OR c.position = :position)")
  Page<EmployeeModel> listByRoleAndPosition(
      @Param("role") Role role, @Param("position") Position position, PageRequest pageRequest);
}
