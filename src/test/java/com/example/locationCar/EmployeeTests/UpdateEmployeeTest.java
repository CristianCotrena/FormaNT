package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.EmployeeUpdateDto;
import com.example.locationCar.mock.employee.EmployeeMockBuilder;
import com.example.locationCar.mock.employee.EmployeeUpdateDtoMockBuilder;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class UpdateEmployeeTest {

  @MockBean private EmployeeRepository employeeRepository;

  @Autowired private UpdateEmployeeService updateEmployeeService;

  private EmployeeUpdateDto employeeUpdateDto;
  private EmployeeModel employeeModel;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    employeeUpdateDto = EmployeeUpdateDtoMockBuilder.build();
    employeeModel = EmployeeMockBuilder.build();
  }

  @Test
  public void updateEmployee_UpdateName_Success() {
    UUID employeeId = UUID.randomUUID();

    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeModel));
    when(employeeRepository.save(any(EmployeeModel.class))).thenReturn(employeeModel);

    BaseDto result = updateEmployeeService.updateEmployee(employeeId, employeeUpdateDto, true);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Funcionário atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateEmployee_AttemptToUpdateContractType_E_CpfCnpj_Failure() {
    UUID employeeId = UUID.randomUUID();
    employeeModel.setCpfCnpj("02516072082");
    employeeModel.setContractType(ContractType.CLT);

    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeModel));

    BaseDto result = updateEmployeeService.updateEmployee(employeeId, employeeUpdateDto, true);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }
}
