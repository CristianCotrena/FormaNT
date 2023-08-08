package com.example.locationCar.controllers;

import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value="id")UUID id,
                                            @RequestBody @Valid ClientRecordDto clientRecordDto) {
        Optional<ClientModel> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        var clienteModel = clientOptional.get();
        BeanUtils.copyProperties(clientRecordDto, clienteModel);
        var saveClient = clientRepository.save(clienteModel);
        Map<String, Object> response = new HashMap<>();
        response.put("idClient", saveClient.getIdClient());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}

