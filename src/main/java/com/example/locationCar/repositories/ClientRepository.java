package com.example.locationCar.repositories;

import com.example.locationCar.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel findByCpfCnpj(String cpfCnpj);
    ClientModel findByEmail(String email);
    ClientModel findByEmailAndCpfCnpj(String email, String cpfCnpj);


}
