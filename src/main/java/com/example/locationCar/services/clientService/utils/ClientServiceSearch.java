package com.example.locationCar.services.clientService.utils;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceSearch {

    private final ClientRepository clientRepository;

    public ClientServiceSearch(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public ClientModel findUser(UUID idClient, String cpfCnpj, String email) {
        List<ClientModel> clients = clientRepository.findAll();
        for(ClientModel client : clients) {
            if (client.getIdClient().equals(idClient) & client.getCpfCnpj().equals(cpfCnpj) & client.getEmail().equals(email)) {
                return client;
            }
        }
        return null;
    }

}

