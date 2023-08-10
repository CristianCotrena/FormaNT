package com.example.locationCar.services.clientService;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SearchClientService {

    private static ClientRepository clientRepository;

    public SearchClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel findUserByEmail(String Email) {
        ClientModel client = clientRepository.findByEmail(Email);
        if (client != null) {
            return client;
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

    }

    public ClientModel findUserByCpfCnpj(String CpfCnpj) {
        ClientModel client = clientRepository.findByCpfCnpj(CpfCnpj);
        if (client != null) {
            return client;
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

    }

    public ClientModel findUserById(UUID id) {
        Optional<ClientModel> client = clientRepository.findById(id);
            if (client.isPresent()) {
                return client.get();
            } else {
                throw new IllegalArgumentException("Cliente não encontrado");
        }
    }
}

