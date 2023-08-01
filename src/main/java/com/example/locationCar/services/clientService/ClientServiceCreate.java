package com.example.locationCar.services.clientService;

import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.utils.ClientRules;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceCreate {

    ClientRepository clientRepository;
    ClientRules clientRules;

    public ClientServiceCreate(ClientRepository clientRepository, ClientRules clientRules) {
        this.clientRepository = clientRepository;
        this.clientRules = clientRules;
    }

    public ClientModel createClient(ClientRecordDto clientRecordDto) {

        ClientModel existsByCNH = clientRepository.findByCnh(clientRecordDto.cnh());
        ClientModel existsByEmail = clientRepository.findByEmail(clientRecordDto.email());

        if(existsByCNH != null) throw new IllegalArgumentException("CNH já cadastrada");
        if(existsByEmail != null) throw new IllegalArgumentException("Email já cadastrado");

        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel); // conversão de DTO para Model
        String encryptedPassword = clientRules.encryptPassword(clientRecordDto.password());
        clientModel.setPassword(encryptedPassword);

        return clientRepository.save(clientModel);

    }
}
