package com.example.locationCar.clientTests;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.DeleteClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteClientTest {
    @InjectMocks
    private DeleteClientService deleteClientService;

    @Mock
    private ClientRepository clientRepository;

    private ClientModel clientModel;

    @BeforeEach
    void setUp() {
        clientModel = new ClientModel();
        clientModel.setIdClient(UUID.randomUUID());
        clientModel.setName("Client");
        clientModel.setCpfCnpj("12345678910");
        clientModel.setCnh("1234567890");
        clientModel.setTelephone("(51) 99988-1234");
        clientModel.setEmail("teste@deleteclient.com.br");
        clientModel.setAge(40);
        clientModel.setEmergencyContact("(51) 99988-4321");
        clientModel.setStatus(1);
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
