package com.example.locationCar.employeeService.Test;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class CreatEmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CreateEmployeeService createEmployeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployee_Valid() {

        EmployeeModel employee = new EmployeeModel();
        employee.setContractType(ContractType.CLT);
        employee.setPosition(Position.VENDEDOR);
        employee.setRole(Role.VENDEDOR);
        employee.setEmail("exemplo@exemplo.com");
        employee.setPhone("(51) 9035-4201");
        employee.setCpfCnpj("41722106000");

        when(employeeRepository.existsByEmail(any())).thenReturn(false);
        when(employeeRepository.existsByCpfCnpj(any())).thenReturn(false);
        when(employeeRepository.save(any(EmployeeModel.class))).thenReturn(employee);

        EmployeeModel savedEmployee = createEmployeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals(employee.getContractType(), savedEmployee.getContractType());
        assertEquals(employee.getPosition(), savedEmployee.getPosition());

        verify(employeeRepository, times(1)).existsByEmail(any());
        verify(employeeRepository, times(1)).existsByCpfCnpj(any());
        verify(employeeRepository, times(1)).save(any(EmployeeModel.class));
    }


}

