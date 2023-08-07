package com.example.locationCar.services.clientServices;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository ;
    }

    public Optional<ClientModel> getClient(UUID id) {
        Optional<ClientModel> client = clientRepository.findById(id);
        return client;
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
