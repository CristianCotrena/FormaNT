package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.mock.employee.EmployeeMockBuilder;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.DeleteEmployeeService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteEmployeeTest {

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private DeleteEmployeeService deleteEmployeeService;

  private EmployeeModel employeeModel;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    deleteEmployeeService = new DeleteEmployeeService(employeeRepository);

    employeeModel = EmployeeMockBuilder.build();
  }

  @Test
  public void deleteEmployee_Success() {
    when(employeeRepository.findById(employeeModel.getEmployeeId()))
        .thenReturn(Optional.of(employeeModel));
    when(employeeRepository.save(employeeModel)).thenReturn(employeeModel);

    EmployeeModel result = deleteEmployeeService.deleteEmployee(employeeModel.getEmployeeId());

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
