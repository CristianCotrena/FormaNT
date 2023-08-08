package com.example.locationCar.controllers;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.CreateClientService;
import com.example.locationCar.services.clientService.ListClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
public class ClientController {

    private final CreateClientService createClientService;
    private final ListClientService listClientService;

    public ClientController(CreateClientService createClientService, ListClientService listClientService) {
        this.listClientService = listClientService;
        this.createClientService = createClientService;
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

    @Operation(summary = "List clients", description = "List clients")
    @ApiResponse(responseCode = "200", description = "Found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ClientModel.class))
    })
    @ApiResponse(responseCode = "404", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Clientes não encontrados.")),
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Idade informada precisa ser maior ou igual a 18.")),
    })
    @GetMapping
    public ResponseEntity<Object> listClients(@RequestParam(required = false) Integer age,
                                              @RequestParam(required = false) Integer page) {
        try {
            Page<ClientModel> clients = listClientService.listClients(age, page);

            if(clients.isEmpty()) return new ResponseEntity<>("Clientes não encontrados.", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
