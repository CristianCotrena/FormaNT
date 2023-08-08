package com.example.locationCar.controllers;

import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.UpdateClientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.CreateClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
public class ClientController {

    private final CreateClientService createClientService;
    private final ClientRepository clientRepository;
    UpdateClientService updateClientService;

    public ClientController(
            CreateClientService createClientService,
            ClientRepository clientRepository) {
        this.createClientService = createClientService;
        this.clientRepository = clientRepository;
    }

    @Operation(summary = "Create client", description = "Add a client to database")
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "E-mail já cadastrado para outro cliente")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF/CNPJ já cadastrado para outro cliente")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF inválido"))
    })
    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody ClientModel clientModel) {
        try {
            UUID newClientId = createClientService.createClient(clientModel);
            return new ResponseEntity<>(newClientId.toString(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                                   @RequestBody ClientRecordDto clientRecordDto) {
        System.out.println("Entrou no update");
        Optional<ClientModel> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        ClientModel client = clientOptional.get();
        BeanUtils.copyProperties(clientRecordDto, client, "idClient");
//        ClientModel updatedClient = updateClientService.updateClient(clientOptional, clientRecordDto);
        return ResponseEntity.ok(clientRepository.save(client));
    }

}

