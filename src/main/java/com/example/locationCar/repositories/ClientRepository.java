package com.example.locationCar.repositories;

import com.example.locationCar.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel findByCnh(String cnh);
    ClientModel findByEmail(String email);
    ClientModel findByEmailAndCnh(String email, String cnh);

}
