package com.example.locationCar.clientTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.CreateClientService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

public class CreateClientTest {

  @MockBean private ClientRepository clientRepository;

  @Mock private CreateClientService createClientService;

  private ClientModel clientModel;

  @BeforeEach
  public void setup() {
    clientRepository = mock(ClientRepository.class);
    createClientService = new CreateClientService(clientRepository);

    clientModel = ClientMockBuilder.build();
  }

  @Test
  public void testCreateClientSuccess() {
    when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
    when(clientRepository.findByCpfCnpj(any(String.class))).thenReturn(Optional.empty());
    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.CREATED.value(), result.getResult().getStatusCode());
    assertEquals("Cliente criado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void testCreateClientDuplicateCpfCnpj() {
    when(clientRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
    when(clientRepository.findByCpfCnpj(any(String.class)))
        .thenReturn(Optional.of(new ClientModel()));
    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
    assertEquals(1, result.getErrors().size());
  }

  @Test
  public void testCreateCLientDuplicateEmail() {
    when(clientRepository.findByEmail(any(String.class)))
        .thenReturn(Optional.of(new ClientModel()));
    when(clientRepository.findByCpfCnpj(any(String.class))).thenReturn(Optional.empty());
    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
    assertEquals(1, result.getErrors().size());
  }

  @Test
  public void testCreateClientInvalidPhone() {
    clientModel.setTelephone("51 98122 0944");

    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);
    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
    assertEquals(1, result.getErrors().size());
  }

  @Test
  public void testInvalidEmail() {
    clientModel.setEmail("anna.hermes.com");

    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);
    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
    assertEquals(1, result.getErrors().size());
  }

  @Test
  public void testEmptyField() {
    clientModel.setName("");
    clientModel.setAge(null);
    clientModel.setTelephone("");

    when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);

    BaseDto result = createClientService.createClient(clientModel);
    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
    assertEquals(3, result.getErrors().size());
  }
}
