package com.example.locationCar.controllers;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientServices.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/client")
@Tag(name = "Client", description = "Operations about Client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
