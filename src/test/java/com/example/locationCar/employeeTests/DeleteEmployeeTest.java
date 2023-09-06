package com.example.locationCar.EmployeeTests;

import com.example.locationCar.controllers.EmployeeController;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.DeleteEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeleteEmployeeTest {

    @Mock
    private DeleteEmployeeService employeeServiceDelete;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeModel employeeModel;

    @BeforeEach
    void setUp() {
        employeeModel = new EmployeeModel();
        employeeModel.setEmployeeId(UUID.randomUUID());
        employeeModel.setName("Teste");
        employeeModel.setCpfCnpj("12345678910");
        employeeModel.setRegistry("123456789");
        employeeModel.setPhone("1212345678");
        employeeModel.setEmail("teste@teste.com");
        employeeModel.setPosition(Position.ESTOQUISTA);
        employeeModel.setContractType(ContractType.CLT);

        // Configurando o comportamento do mock do EmployeeServiceDelete
        when(employeeServiceDelete.deleteEmployee(any(UUID.class))).thenReturn(employeeModel);
    }

    @Test
    public void deleteEmployee() {
        // a partir de um funcionario já criado, recuperar seu id e deletá-lo por meio dele.

        UUID funcionarioId = employeeModel.getEmployeeId();

        employeeRepository.deleteById(funcionarioId);

        // verificar se o funcionario foi deletado

        assertFalse(employeeRepository.existsById(funcionarioId));
    }
}
