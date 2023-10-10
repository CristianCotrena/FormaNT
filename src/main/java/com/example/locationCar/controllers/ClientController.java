package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.ClientUpdateDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.*;
import com.example.locationCar.services.clientService.CreateClientService;
import com.example.locationCar.services.clientService.DeleteClientService;
import com.example.locationCar.services.clientService.SearchClientService;
import com.example.locationCar.services.clientService.UpdateClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations about client")
@AllArgsConstructor
public class ClientController {
  private final ListClientService listClientService;
  private final CreateClientService createClientService;
  private final UpdateClientService updateClientService;
  private final SearchClientService searchClientService;
  private final DeleteClientService deleteClientService;

  @Operation(summary = "Search for a client", description = "Search a client into database")
  @ApiResponse(
      responseCode = "200",
      description = "Founded",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "customer found successfully"))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Client not found")),
      })
  @GetMapping
  public ResponseEntity<Object> searchClient(
      @Schema(description = "First create a client in POST, then put his ID here.")
          @RequestParam(value = "id", required = false)
          UUID idClient,
      @Schema(description = "CPF or CNPJ that you created in POST")
          @RequestParam(value = "cpfCnpj", required = false)
          String cpfCnpj,
      @Schema(description = "Email that you created in POST")
          @RequestParam(value = "email", required = false)
          String email) {

    try {
      if (idClient != null) {
        ClientModel clientModel = searchClientService.findUserById(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
      } else if (cpfCnpj != null) {
        ClientModel clientModel = searchClientService.findUserByCpfCnpj(cpfCnpj);
        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
      } else if (email != null) {
        ClientModel clientModel = searchClientService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
      } else {
        return ResponseEntity.badRequest().body("Customer not found, check posted values.");
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
          .body("Enter a valid ID, CPF/CNPJ or email to pick up the customer.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Enter a valid ID, Cpf/Cnpj or Email.");
    }
  }

  @Operation(summary = "Create client", description = "Add a client to database")
  @ApiResponse(
      responseCode = "201",
      description = "Created",
      content = {
        @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "E-mail já cadastrado para outro cliente")),
        @Content(
            mediaType = "text/plain",
            schema =
                @Schema(type = "string", example = "CPF/CNPJ já cadastrado para outro cliente")),
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "CPF inválido"))
      })
  @PostMapping
  public BaseDto createClient(@RequestBody ClientModel clientModel) {
    return createClientService.createClient(clientModel);
  }

  @Operation(summary = "Update client", description = "Update client")
  @ApiResponse(
      responseCode = "200",
      description = "Updated",
      content = {
        @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Telefone inválido")),
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Cliente não encontrado"))
      })
  @PutMapping("/{id}")
  public ResponseEntity<String> updateClient(
      @PathVariable(value = "id") UUID id, @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
    try {
      return updateClientService.updateClient(id, clientUpdateDto);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @Operation(summary = "List clients", description = "List clients")
  @ApiResponse(
      responseCode = "200",
      description = "Found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ClientModel.class))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Clientes não encontrados.")),
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "application/json",
            schema =
                @Schema(
                    type = "string",
                    example = "Idade informada precisa ser maior ou igual a 18.")),
      })
  @GetMapping("/list")
  public ResponseEntity<BaseDto> listClients(
      @RequestParam(required = false) String age, @RequestParam(required = false) String page) {
    BaseDto baseDto = listClientService.listClients(age, page);

    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(summary = "Delete Client", description = "Delete an client to database")
  @ApiResponse(
      responseCode = "404",
      description = "Client not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Client not found")),
      })
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Client deleted successfully"))
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
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error, please enter only your Client ID.");
    }
  }

  @DeleteMapping("/")
  public ResponseEntity<Object> deleteClientError() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, Id cannot be null");
  }
}
