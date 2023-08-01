package com.example.locationCar.controllers;

import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.ClientServiceCreate;
import com.example.locationCar.services.clientService.utils.ClientRules;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClientController {

    ClientRepository clientRepository;
    ClientRules clientRules;
    ClientServiceCreate clientServiceCreate;

    public ClientController(
            ClientRepository clientRepository,
            ClientRules clientRules,
            ClientServiceCreate clientServiceCreate)
    {
        this.clientRepository = clientRepository;
        this.clientRules = clientRules;
        this.clientServiceCreate = clientServiceCreate;
    }

    @PostMapping("/v1/client")
    public ResponseEntity<UUID> createUser(@RequestBody @Valid ClientRecordDto clientRecordDto) {
        var addClient = clientServiceCreate.createClient(clientRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addClient.getIdClient());
    }

}
