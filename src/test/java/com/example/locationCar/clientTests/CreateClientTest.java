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
        clientModel.setCnh("123456789023");
        clientModel.setEmail("teste@teste.com");
        clientModel.setAge(25);
        clientModel.setTelephone("11999999999");
        clientModel.setEmergencyContact("11999999998");
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
    public void testCreateClient_CpfCnpjAlreadyExists() {
        // Dados de entrada para o teste
        String sameCpfCnpj = "13234545899"; // cpf/cnpj que já existe no DB. No caso de banco de dados já populado, é fazer um filter e encontrar a informação correspondente

        // Configuração do mock para lançar uma exceção se o cpf/cnpj já existir
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getCpfCnpj().equals(sameCpfCnpj)) {
                throw new IllegalArgumentException("CPF/CNPJ já cadastrado para outro cliente.");
            }
            // Se o cpf/cnpj não existir, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("CPF/CNPJ já cadastrado para outro cliente.", responseEntity.getBody());
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

    @Test
    public void testCreateClient_telephoneValidation() {

        // Configuração do mock para lançar uma exceção se o telefone for inválido
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getTelephone().length() < 10 || client.getTelephone().length() > 11) {
                throw new IllegalArgumentException("Telefone inválido.");
            }
            // Se o telefone for válido, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Obtendo o ID criado a partir do corpo da resposta
        String createdId = responseEntity.getBody();

        // Verificando se o ID criado não é nulo e não está vazio
        assertNotNull(createdId);
        assertFalse(createdId.isEmpty());
    }


    //    cpf válido
    @Test
    public void testCreateClient_CpfCnpjValidation() {

        // Configuração do mock para lançar uma exceção se o cpf/cnpj for inválido
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getCpfCnpj().length() < 11 || client.getCpfCnpj().length() > 14) {
                throw new IllegalArgumentException("CPF/CNPJ inválido.");
            }
            // Se o cpf/cnpj for válido, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Obtendo o ID criado a partir do corpo da resposta
        String createdId = responseEntity.getBody();

        // Verificando se o ID criado não é nulo e não está vazio
        assertNotNull(createdId);
        assertFalse(createdId.isEmpty());
    }

    //    email válido
    @Test
    public void testCreateClient_EmailValidation() {

        // Dados de entrada para o teste
        String invalidEmail = "teste@teste"; // email inválido

        // Configuração do mock para lançar uma exceção se o email for inválido
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getEmail().contains(invalidEmail)) {
                throw new IllegalArgumentException("E-mail inválido.");
            }
            // Se o email for válido, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("E-mail inválido.", responseEntity.getBody());
    }


    //    cnh válida
    @Test
    public void testCreateClient_CnhValidation() {

        // Configuração do mock para lançar uma exceção se a Cnh for inválida
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getCnh().length() < 10 || client.getCnh().length() > 12) {
                throw new IllegalArgumentException("CNH inválida.");
            }
            // Se o telefone for válido, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Obtendo o ID criado a partir do corpo da resposta
        String createdId = responseEntity.getBody();

        // Verificando se o ID criado não é nulo e não está vazio
        assertNotNull(createdId);
        assertFalse(createdId.isEmpty());
    }

    //    ermergencyContact válido
    @Test
    public void testCreateClient_ErmergencyContactValidation() {
        // Configuração do mock para lançar uma exceção se o telefone for inválido
        when(clientServiceCreate.createClient(any())).thenAnswer(invocation -> {
            ClientModel client = invocation.getArgument(0); // Obtém o argumento passado para o método createClient, que é o clientModel
            if (client.getEmergencyContact().length() < 10 || client.getEmergencyContact().length() > 11) {
                throw new IllegalArgumentException("Telefone do contato de emergência inválido.");
            }
            // Se o telefone for válido, retorne um UUID válido
            return UUID.randomUUID();
        });

        // Executando o método a ser testado
        ResponseEntity<String> responseEntity = clientController.createClient(clientModel);

        // Verificando se o resultado foi o esperado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Obtendo o ID criado a partir do corpo da resposta
        String createdId = responseEntity.getBody();

        // Verificando se o ID criado não é nulo e não está vazio
        assertNotNull(createdId);
        assertFalse(createdId.isEmpty());
    }
}