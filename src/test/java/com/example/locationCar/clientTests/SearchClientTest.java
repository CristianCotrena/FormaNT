package com.example.locationCar.clientTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.SearchClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SearchClientTest {

  @Mock private SearchClientService searchClientService;

  @InjectMocks private ClientController clientController;

  private ClientModel clientModel;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    clientModel = ClientMockBuilder.build();
  }

  @Test
  public void testSearchClient_ClientFoundById() {
    when(searchClientService.findUserById(clientModel.getIdClient())).thenReturn(clientModel);

    ResponseEntity<Object> response =
        clientController.searchClient(clientModel.getIdClient(), null, null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(clientModel, response.getBody());
  }

  @Test
  public void testSearchClient_ClientFoundByCpfCnpj() {
    when(searchClientService.findUserByCpfCnpj(clientModel.getCpfCnpj())).thenReturn(clientModel);

    ResponseEntity<Object> response =
        clientController.searchClient(null, clientModel.getCpfCnpj(), null);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(clientModel, response.getBody());
  }

  @Test
  public void testSearchClient_ClientFoundByEmail() {
    when(searchClientService.findUserByEmail(clientModel.getEmail())).thenReturn(clientModel);

    ResponseEntity<Object> response =
        clientController.searchClient(null, null, clientModel.getEmail());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(clientModel, response.getBody());
  }
}
