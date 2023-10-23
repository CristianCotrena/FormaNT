package com.example.locationCar.clientTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.DeleteClientService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteClientTest {
  @InjectMocks private DeleteClientService deleteClientService;

  @Mock private ClientRepository clientRepository;

  private ClientModel clientModel;

  @BeforeEach
  void setUp() {
    clientModel = ClientMockBuilder.build();
  }

  @Test
  public void testGetClientById() {
    UUID id = clientModel.getIdClient();

    when(clientRepository.findById(id)).thenReturn(Optional.of(clientModel));

    Optional<ClientModel> result = deleteClientService.getClient(id);

    assertNotNull(result);
    assertEquals(clientModel, result.get());
  }

  @Test
  public void deleteClient() {

    UUID clientId = clientModel.getIdClient();

    clientRepository.deleteById(clientId);

    assertFalse(clientRepository.existsById(clientId));
  }
}
