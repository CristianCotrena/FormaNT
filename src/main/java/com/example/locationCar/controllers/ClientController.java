package com.example.locationCar.controllers;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.CreateClientService;
import com.example.locationCar.services.clientService.SearchClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import com.example.locationCar.services.clientService.DeleteClientService;

import java.util.Optional;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
public class ClientController {

    CreateClientService createClientService;
    SearchClientService searchClientService;
    DeleteClientService deleteClientService;



    public ClientController(DeleteClientService deleteClientService,
                            CreateClientService createClientService,
                            SearchClientService searchClientService) {
        this.deleteClientService = deleteClientService;
        this.createClientService = createClientService;
        this.searchClientService = searchClientService;
    }

    @Operation(summary = "Search for a client", description = "Search a client into database")
    @ApiResponse(responseCode = "200", description = "Founded", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "customer found successfully"))
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Client not found")),
    })
    @GetMapping
    public ResponseEntity<Object> searchClient(
            @Schema(description = "First create a client in POST, then put his ID here.")
            @RequestParam(value = "id", required = false) UUID idClient,
            @Schema(description = "CPF or CNPJ that you created in POST")
            @RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
            @Schema(description = "Email that you created in POST")
            @RequestParam(value = "email", required = false) String email) {

        if (idClient != null) {
            ClientModel clientModel = searchClientService.findUserById(idClient);
            return ResponseEntity.status(HttpStatus.OK).body(clientModel);
        }

        if (cpfCnpj != null) {
            ClientModel clientModel = searchClientService.findUserByCpfCnpj(cpfCnpj);
            return ResponseEntity.status(HttpStatus.OK).body(clientModel);
        }

        if (email != null) {
            ClientModel clientModel = searchClientService.findUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(clientModel);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not found");
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

    @Operation(summary = "Delete Client", description = "Delete an client to database")
    @ApiResponse(responseCode = "404", description = "Client not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Client not found")),
    })
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Client deleted successfully"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") String id) {
        try {
            UUID idClient = UUID.fromString(id);
            Optional<ClientModel> client = deleteClientService.getClient(idClient);
            if (client.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
            }
            deleteClientService.deleteClient(idClient);
            return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro, please enter only your Client ID.");
        }
    }
}
