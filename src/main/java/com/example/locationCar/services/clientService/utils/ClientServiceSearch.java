package com.example.locationCar.services.clientService.utils;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceSearch {

    private static ClientRepository clientRepository;

    public ClientServiceSearch(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public ClientModel findUser(UUID idClient, String cpfCnpj, String email) {
        List<ClientModel> clients = clientRepository.findAll();
        for(ClientModel client : clients) {
            if (idClient.equals(client.getIdClient()) && cpfCnpj.equals(client.getCpfCnpj()) && email.equals(client.getEmail())) {
                return client;
            }
        }
        return null;
    }
}

