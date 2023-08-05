package com.example.locationCar.clientTests;


import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.utils.ClientServiceSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchClientTest {

    @Mock
    private ClientServiceSearch clientServiceSearch;

    @InjectMocks
    private ClientController clientController;

    private ClientModel clientModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Cofiguração de um client de exemplo
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
    public void testSearchClient_ClientFound() {
        // Configurção do comportamento simulado do clientServiceSearch
        when(clientServiceSearch.findUser(clientModel.getIdClient(),clientModel.getCpfCnpj(), clientModel.getEmail()))
                .thenReturn(clientModel);

        // Chamada do método que será testado
        ResponseEntity<Object> response = clientController.searchClient(clientModel.getIdClient(),
                clientModel.getCpfCnpj(), clientModel.getEmail());

        // Verificação do resultado esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientModel, response.getBody());
    }

        //Simula o cenário em que o cliente não é encontrado.
    @Test
    public void testSearchClient_ClientNotFound() {
        // Configuração do comportamento simulado do clientServiceSearch
        when(clientServiceSearch.findUser(any(), any(), any())).thenReturn(null);

        // Chamada do método que será testado
        ResponseEntity<Object> response = clientController.searchClient(UUID.randomUUID(), "999999999",
                "naoexiste@teste.com");

        // Verificação do resultado esperado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Client not found", response.getBody());
    }

}

