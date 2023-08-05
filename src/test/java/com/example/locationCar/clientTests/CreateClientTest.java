package com.example.locationCar.clientTests;

import com.example.locationCar.controllers.ClientController;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.services.clientService.ClientServiceCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreateClientTest {

    @Mock
    private ClientServiceCreate clientServiceCreate; // Simula o serviço que cria o cliente

    @InjectMocks
    private ClientController clientController; // Simula o controller que recebe a requisição

    private ClientModel clientModel; // Armazena os dados de entrada para o teste

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        clientModel = new ClientModel(); // Inicializa o objeto clientModel antes de cada teste
        clientModel.setName("Guilherme Lemos");
        clientModel.setCpfCnpj("13234545899");
        clientModel.setCnh("123456789");
        clientModel.setEmail("teste@teste.com");
        clientModel.setAge(25);
        clientModel.setTelephone("11999999999");
        clientModel.setEmergencyContact("11999999399");
        clientModel.setStatus(1);
        clientModel.setPassword("123456");
    }

    @Test
    public void testCreateClient_Success() {
        UUID validUUID = UUID.randomUUID();

        // Criando cliente e retornando o id
        when(clientServiceCreate.createClient(any())).thenReturn(validUUID);

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateClient_CnhAlreadyExists() {
        // Dados de entrada para o teste
        String sameCNH = "123456789"; // CNH que já existe no DB. No caso de banco de dados já populado, é fazer um filter e encontrar a cnh correspondente

        // Configuração do mock para lançar uma exceção se o CNH já existir
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getCnh().equals(sameCNH)) {
                throw new IllegalArgumentException("CNH já cadastrada para outro cliente.");
            }
            // Se o CNH não existir, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
            ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("CNH já cadastrada para outro cliente.", responseEntity.getBody());
    }

    @Test
    public void testCreateClient_EmailAlreadyExists() {
            // Dados de entrada para o teste
            String sameEmail = "teste@teste.com"; // Email que já existe no DB. No caso de banco de dados já populado, é fazer um filter e encontrar o Email correspondente

            // Configuração do mock para lançar uma exceção se o Email já existir
            when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
                ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
                if (client.getEmail().equals(sameEmail)) {
                    throw new IllegalArgumentException("E-mail já cadastrado para outro cliente.");
                }
                // Se o Email não existir, retorne um UUID válido
                return UUID.randomUUID();
            });

            // Executando o método a ser testado
            ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

            // Verificando se o resultado foi o esperado
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
            assertEquals("E-mail já cadastrado para outro cliente.", responseEntity.getBody());
    }
}
