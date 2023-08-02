package com.example.locationCar.services.clientService;

import ch.qos.logback.core.net.server.Client;
import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.utils.ClientRules;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceCreate {
    private final ClientRepository clientRepository;
    private final ClientRules clientRules;

    @Autowired
    public ClientServiceCreate(ClientRepository clientRepository, ClientRules clientRules) {
        this.clientRepository = clientRepository;
        this.clientRules = clientRules;
    }

    public UUID createClient(ClientModel clientModel) {

        ClientModel existingClient = clientRepository.findByEmailAndCnh(clientModel.getEmail(), clientModel.getCnh());
        if (existingClient != null){
            throw new IllegalArgumentException("CNH e E-mail já cadastrados para outro cliente.");
        }
        if (clientRepository.findByCnh(clientModel.getCnh()) != null){
            throw new IllegalArgumentException("CNH já cadastrada para outro cliente.");
        }
        if (clientRepository.findByEmail(clientModel.getEmail()) != null){
            throw new IllegalArgumentException("E-mail já cadastrado para outro cliente.");
        }

        try {
            String encryptedPassword = clientRules.encryptPassword(clientModel.getPassword());
            clientModel.setPassword(encryptedPassword);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Erro na solicitação de cadastro.");
        }

        return clientRepository.save(clientModel).getIdClient();
    }
}
