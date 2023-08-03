package com.example.locationCar.controllers;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.ClientServiceCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
public class ClientController {

   private final ClientServiceCreate clientServiceCreate;

    @Autowired
    public ClientController(ClientServiceCreate clientServiceCreate) {
        this.clientServiceCreate = clientServiceCreate;
    }

    @Operation(summary = "Create client", description = "Add a client to database")
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "E-mail já cadastrado para outro cliente")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF/CNPJ já cadastrado para outro cliente")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CNPJ inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "E-mail inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CNH inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Telefone inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Telefone do contato de emergência inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF/CNPJ inválido")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "E-mail e CPF/CNPJ já cadastrados para outro cliente")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Erro na solicitação de cadastro."))
    })
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
