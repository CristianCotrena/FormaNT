package com.example.locationCar.employeeService.Test;

import com.example.locationCar.controllers.EmployeeController;
import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

//import static com.example.locationCar.services.employeeService.CreateEmployeeService.employeeRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateEmployeeTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UpdateEmployeeService updateEmployeeService;

    private EmployeeModel employeeModel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        employeeModel = new EmployeeModel();
        employeeModel.setEmployeeId(UUID.randomUUID());
        employeeModel.setName("Adrielly");
        employeeModel.setPosition(Position.VENDEDOR);
        employeeModel.setCpfCnpj("91322082006");
        employeeModel.setRegistry("4321");
        employeeModel.setPhone("(51) 3334-0436");
        employeeModel.setContractType(ContractType.CLT);
        employeeModel.setRole(Role.ADMINISTRADOR);
        employeeModel.setEmail("joana@gmail.com");
        employeeModel.setStatus(2);
    }

    @Test
    public void testUpdateEmployee_Valid() {
        UUID employeeId = UUID.fromString("4c38c805-e0a4-4b19-ae13-582cbcaac807");

        // Define os valores originais que não podem ser alterados
        EmployeeModel originalEmployee = new EmployeeModel();
        originalEmployee.setEmployeeId(employeeId);
        originalEmployee.setEmail("joana@gmail.com");
        originalEmployee.setCpfCnpj("91322082006");
        originalEmployee.setContractType(ContractType.CLT);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(originalEmployee));
        EmployeeRecordDto employeeRecordDto = null;
        when(updateEmployeeService.updateEmployee(employeeId, employeeRecordDto)).thenReturn(employeeId);

        // Simula a atualização do funcionário
        employeeRecordDto = new EmployeeRecordDto(
                "New Name",
                Position.VENDEDOR,
                "02516072082",
                "4321",
                "(51) 9035-4201",
                ContractType.CLT,
                Role.ADMINISTRADOR,
                "new@example.com",
                2
        );

        when(updateEmployeeService.updateEmployee(employeeId, employeeRecordDto)).thenReturn(employeeId);

        // Chama o método a ser testado
        ResponseEntity<?> updatedEmployeeId = employeeController.updateEmployee(employeeId, employeeRecordDto);


        assertEquals(HttpStatus.OK, updatedEmployeeId.getStatusCode());
        String updatedEmployeeIdString = updatedEmployeeId.getBody().toString();
        assertEquals(employeeId.toString(), updatedEmployeeIdString);

        // Verifica se os valores não alteráveis permaneceram os mesmos
        assertEquals("joana@gmail.com", originalEmployee.getEmail());
        assertEquals("91322082006", originalEmployee.getCpfCnpj());
        assertEquals(ContractType.CLT, originalEmployee.getContractType());
    }

    @Test
    public void deveriaValidarTelefone() {
        String telefoneValido = "(51) 4825-9918";
        String telefoneInvalido = "(51) 378 2578";
        assertEquals(true, updateEmployeeService.isValidTelefone(telefoneValido));
        assertEquals(false, updateEmployeeService.isValidTelefone(telefoneInvalido));
    }

    @Test
    public void deveriaValidarPosition(){
        EmployeeRepository employeeRepositoryMock = mock(EmployeeRepository.class);
        UpdateEmployeeService updateEmployeeService = new UpdateEmployeeService(employeeRepositoryMock);

        // Testar Position VENDEDOR (deve ser válido)
       boolean isValidPositionVendedor = updateEmployeeService.validatePosition(Position.VENDEDOR);
        assertTrue(isValidPositionVendedor);

        // Testar Position ESTOQUISTA (deve ser válido)
        boolean isValidPositionEstoquista = updateEmployeeService.validatePosition(Position.ESTOQUISTA);
        assertTrue(isValidPositionEstoquista);


    }
}

