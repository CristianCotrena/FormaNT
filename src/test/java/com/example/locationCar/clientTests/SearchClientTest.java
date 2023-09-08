package com.example.locationCar.clientTests;

import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.SearchClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SearchClientTest {

    @Mock
    private SearchClientService searchClientService;

    @InjectMocks
    private ClientController clientController;

    private ClientModel clientModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Configuração de um client de exemplo
        UUID idClient = UUID.randomUUID();
        String cpfCnpj = "12345678900";
        String email = "client@teste.com";

        clientModel = new ClientModel();
        clientModel.setIdClient(idClient);
        clientModel.setCpfCnpj(cpfCnpj);
        clientModel.setEmail(email);
    }

        //Simula o cenário em que o cliente é encontrado pelo ID, CPF/CNPJ e e-mail.

   @Test
    public void testSearchClient_ClientFoundById() {
        // Configuração do comportamento simulado do clientServiceSearch
        when(searchClientService.findUserById(clientModel.getIdClient()))
                .thenReturn(clientModel);

        // Chamada do método que será testado
        ResponseEntity<Object> response = clientController.searchClient(clientModel.getIdClient(), null, null);

        // Verificação do resultado esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientModel, response.getBody());
    }

    @Test
    public void testSearchClient_ClientFoundByCpfCnpj() {
        // Configuração do comportamento simulado do clientServiceSearch
        when(searchClientService.findUserByCpfCnpj(clientModel.getCpfCnpj()))
                .thenReturn(clientModel);

        // Chamada do método que será testado
        ResponseEntity<Object> response = clientController.searchClient(null, clientModel.getCpfCnpj(), null);

        // Verificação do resultado esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientModel, response.getBody());
    }

    @Test
    public void testSearchClient_ClientFoundByEmail() {
        // Configuração do comportamento simulado do clientServiceSearch
        when(searchClientService.findUserByEmail(clientModel.getEmail()))
                .thenReturn(clientModel);

        // Chamada do método que será testado
        ResponseEntity<Object> response = clientController.searchClient(null, null, clientModel.getEmail());

        // Verificação do resultado esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientModel, response.getBody());
    }

}
