package com.example.locationCar.clientTests;

import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.ListClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListClientTest {

    @Mock
    private ListClientService listClientService;

    @InjectMocks
    private ClientController clientController;

    private List<ClientModel> clients;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        clients = new ArrayList<>();
        clients.add(new ClientModel(UUID.fromString("771079a3-7d31-433c-aae8-24a14b2b8c46"), "Cliente 1", "435.763.342-21", "874658935467", 12, "11999999999", "11999999999", "cliente1@gmail.com", 1, "12346"));
        clients.add(new ClientModel(UUID.fromString("771079a3-7d31-433c-aae8-24a14b2b8c47"), "Cliente 2", "435.763.342-22", "874658935467", 25, "11999999999", "11999999999", "cliente2@gmail.com", 1, "12346"));
        clients.add(new ClientModel(UUID.fromString("771079a3-7d31-433c-aae8-24a14b2b8c48"), "Cliente 3", "435.763.342-23", "874658935467", 18, "11999999999", "11999999999", "cliente3@gmail.com", 1, "12346"));
        clients.add(new ClientModel(UUID.fromString("771079a3-7d31-433c-aae8-24a14b2b8c49"), "Cliente 4", "435.763.342-24", "874658935467", 32, "11999999999", "11999999999", "cliente4@gmail.com", 1, "12346"));
    }

    @Test
    public void testListClients_AllClients() {
        when(listClientService.listClients(null, null)).thenReturn(new PageImpl<>(clients));

        ResponseEntity<Object> responseEntity = clientController.listClients(null, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clients, ((Page<ClientModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListClients_AgeFilter() {
        int age = 25;
        List<ClientModel> filteredClients = new ArrayList<>();
        for (ClientModel client : clients) {
            if (client.getAge() >= age) {
                filteredClients.add(client);
            }
        }

        when(listClientService.listClients(age, null)).thenReturn(new PageImpl<>(filteredClients));

        ResponseEntity<Object> responseEntity = clientController.listClients(age, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filteredClients, ((Page<ClientModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListClients_AgeFilterWithPage() {
        int age = 25;
        int page = 1;
        List<ClientModel> filteredClients = new ArrayList<>();
        for (ClientModel client : clients) {
            if (client.getAge() >= age) {
                filteredClients.add(client);
            }
        }

        when(listClientService.listClients(age, page)).thenReturn(new PageImpl<>(filteredClients));

        ResponseEntity<Object> responseEntity = clientController.listClients(age, page);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filteredClients, ((Page<ClientModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListClients_InvalidAge() {
        int age = 17;
        String errorMessage = "Idade informada precisa ser maior ou igual a 18.";

        when(listClientService.listClients(age, null)).thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<Object> responseEntity = clientController.listClients(age, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}