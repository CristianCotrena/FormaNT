package com.example.locationCar.controllers;


import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.ClientServiceCreate;
import com.example.locationCar.services.clientService.utils.ClientServiceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/client")
public class ClientController {

    ClientServiceCreate clientServiceCreate;
    ClientServiceSearch clientServiceSearch;

    @Autowired
    public ClientController(ClientServiceCreate clientServiceCreate, ClientServiceSearch clientServiceSearch) {
        this.clientServiceCreate = clientServiceCreate;
        this.clientServiceSearch = clientServiceSearch;
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

    @GetMapping
    public ResponseEntity<Object> searchClient(@RequestParam(value = "id") UUID idClient,
                                               @RequestParam(value = "cpfCnpj") String cpfCnpj,
                                               @RequestParam(value = "email") String email) {
        ClientModel client = clientServiceSearch.findUser(idClient, cpfCnpj, email);

        if (client != null) {
            if (client.getIdClient().equals(idClient) && client.getCpfCnpj().equals(cpfCnpj) && client.getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.OK).body(client);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
    }
}
