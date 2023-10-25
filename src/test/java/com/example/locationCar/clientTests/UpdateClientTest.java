package com.example.locationCar.clientTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.dtos.ClientUpdateDto;
import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.UpdateClientService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UpdateClientTest {
  @Mock private UpdateClientService updateClientService;

  @InjectMocks private ClientController clientController;

  private ClientModel clientModel;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    clientModel = ClientMockBuilder.build();
  }

  @Test
  public void testUpdateClient_Success() {
    UUID clientId = UUID.fromString("4c38c805-e0a4-4b19-ae13-582cbcaac807");

    ClientUpdateDto clientUpdateDto =
        new ClientUpdateDto(
            "Novo Nome",
            "123456787777",
            "12345677",
            25,
            "11952222222",
            "11952223333",
            "user@domain.com.br");

    when(updateClientService.updateClient(clientId, clientUpdateDto))
        .thenReturn(ResponseEntity.ok(clientId.toString()));

    ResponseEntity<String> responseEntity =
        clientController.updateClient(clientId, clientUpdateDto);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(clientId.toString(), responseEntity.getBody());
  }

  @Test
  public void testUpdateClient_InvalidData_Phone() {
    UUID clientId = UUID.fromString("4c38c805-e0a4-4b19-ae13-582cbcaac807");

    ClientUpdateDto clientUpdateDto =
        new ClientUpdateDto(
            "Novo Nome",
            "123456787777",
            "12345677",
            25,
            "1234",
            "11952223333",
            "user@domain.com.br");

    when(updateClientService.updateClient(clientId, clientUpdateDto))
        .thenThrow(new IllegalArgumentException("Telefone inválido."));

    ResponseEntity<String> responseEntity =
        clientController.updateClient(clientId, clientUpdateDto);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Telefone inválido.", responseEntity.getBody());
  }

  @Test
  public void testUpdateClient_InvalidData_Age() {
    UUID clientId = UUID.fromString("4c38c805-e0a4-4b19-ae13-582cbcaac807");

    ClientUpdateDto clientUpdateDto =
        new ClientUpdateDto(
            "Novo Nome",
            "123456787777",
            "12345677",
            15,
            "11952222222",
            "11952223333",
            "user@domain.com.br");

    when(updateClientService.updateClient(clientId, clientUpdateDto))
        .thenThrow(new IllegalArgumentException("Idade inválida."));

    ResponseEntity<String> responseEntity =
        clientController.updateClient(clientId, clientUpdateDto);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Idade inválida.", responseEntity.getBody());
  }
}
