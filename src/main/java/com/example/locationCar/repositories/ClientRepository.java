package com.example.locationCar.repositories;

import com.example.locationCar.models.ClientModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
  Optional<ClientModel> findByCpfCnpj(String cpfCnpj);

  Optional<ClientModel> findByEmail(String email);

  ClientModel findByEmailAndCpfCnpj(String email, String cpfCnpj);

  @Query("SELECT c FROM ClientModel c WHERE (:age IS NULL OR c.age = :age)")
  Page<ClientModel> listByAge(Integer age, PageRequest pageRequest);
}
