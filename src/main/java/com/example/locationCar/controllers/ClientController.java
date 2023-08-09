package com.example.locationCar.controllers;

import com.example.locationCar.dtos.ClientUpdateDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.CreateClientService;
import com.example.locationCar.services.clientService.UpdateClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.UUID;

import com.example.locationCar.services.clientServices.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
public class ClientController {
    private final CreateClientService createClientService;
    private final UpdateClientService updateClientService;
    private final ClientService clientService;

    public ClientController(ClientService clientService, CreateClientService createClientService, UpdateClientService updateClientService) {
        this.clientService = clientService;
        this.createClientService = createClientService;
        this.updateClientService = updateClientService;
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

    @Operation(summary = "Update client", description = "Update client")
    @ApiResponse(responseCode = "200", description = "Updated", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Telefone inválido")),
    })
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Cliente não encontrado"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        try{
            return updateClientService.updateClient(id, clientUpdateDto);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete Client", description = "Delete an client to database")
    @ApiResponse(responseCode = "404", description = "Client not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Client not found")),
    })
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Client deleted successfully"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> client = clientService.getClient(id);
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }
}
