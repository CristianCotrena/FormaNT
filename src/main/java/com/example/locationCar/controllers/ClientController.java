package com.example.locationCar.controllers;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.ClientServiceCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/client")
public class ClientController {

   ClientServiceCreate clientServiceCreate;

    @Autowired
    public ClientController(ClientServiceCreate clientServiceCreate) {
        this.clientServiceCreate = clientServiceCreate;
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody ClientModel clientModel) {
        try {
            UUID newClientId = clientServiceCreate.createClient(clientModel);
            return new ResponseEntity<>(newClientId.toString(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
