package com.example.locationCar.employeeService.Test;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateEmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private UpdateEmployeeService updateEmployeeService;

    private EmployeeModel employeeModel;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        employeeModel = new EmployeeModel();
        employeeModel.setEmployeeId(UUID.randomUUID());
        employeeModel.setName("Joana");
        employeeModel.setPosition(Position.VENDEDOR);
        employeeModel.setCpfCnpj("54506305302");
        employeeModel.setRegistry("1234");
        employeeModel.setPhone("5133340436");
        employeeModel.setContractType(ContractType.CLT);
        employeeModel.setRole(Role.VENDEDOR);
        employeeModel.setEmail("joana@gmail.com");
        employeeModel.setStatus(1);
    }

    EmployeeRecordDto employeeRecordDto = new EmployeeRecordDto(
            "New Name",
            Position.ESTOQUISTA,
            "54506305302",
            "4321",
            "(51) 9035-4201",
            ContractType.CNPJ,
            Role.ADMINISTRADOR,
            "new@example.com",
            2
    );

    @Test
    public void testUpdateEmployee_Valid() {
        UUID employeeId = employeeModel.getEmployeeId();


        // Simula os dados do funcionário existentes no banco de dados
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeModel));

        // Atualizando Funcionario
        UUID updatedEmployeeId = updateEmployeeService.updateEmployee(employeeId, employeeRecordDto);

        // Validando o resultado
        assertEquals(employeeId, updatedEmployeeId);
        assertEquals("New Name", employeeModel.getName());
        assertEquals(Position.ESTOQUISTA, employeeModel.getPosition());
        assertEquals("4321", employeeModel.getRegistry());
        assertEquals(Role.ADMINISTRADOR, employeeModel.getRole());
        assertEquals(2, employeeModel.getStatus());

        // Verifica se os métodos necessários do repositório foram chamados
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(employeeModel);
    }

    @Test
    public void testUpdateEmployee_InvalidId() {
        UUID nonExistentId = UUID.randomUUID();

        // Simula a ausência de dados de funcionário existentes no banco de dados
        when(employeeRepository.findById(nonExistentId)).thenReturn(Optional.empty());



        // Realiza a operação de atualização e espera por uma exceção
        assertThrows(IllegalArgumentException.class,
                () -> updateEmployeeService.updateEmployee(nonExistentId, employeeRecordDto));

        verify(employeeRepository, times(1)).findById(nonExistentId);
        verify(employeeRepository, times(0)).save(any());
    }
}