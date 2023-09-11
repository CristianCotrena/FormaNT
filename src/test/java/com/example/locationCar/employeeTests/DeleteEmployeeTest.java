package com.example.locationCar.EmployeeTests;

import com.example.locationCar.base.dto.BaseDto;
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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeleteEmployeeTest {

    @Mock
    private DeleteEmployeeService employeeServiceDelete;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DeleteEmployeeService deleteEmployeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa os mocks

        // Instancia o serviço a ser testado, passando os mocks configurados
        deleteEmployeeService = new DeleteEmployeeService(employeeRepository);
    }

    @Test
    public void deleteEmployee_Success() {
        UUID employeeId = UUID.randomUUID();
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeId(employeeId);
        employee.setStatus(1);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeModel result = deleteEmployeeService.deleteEmployee(employeeId);

        assertNotNull(result);
        assertEquals(0, result.getStatus());


    }

    @Test
    public void deleteEmployee_NotFound() {
        UUID employeeId = UUID.randomUUID();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        EmployeeModel result = deleteEmployeeService.deleteEmployee(employeeId);

        assertNull(result);
    }
}
