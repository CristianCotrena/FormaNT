package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import com.example.locationCar.validate.employee.UpdateEmployeeValidate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class UpdateEmployeeTest {

  @Mock private EmployeeRepository employeeRepository;

  private UpdateEmployeeService updateEmployeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    updateEmployeeService = new UpdateEmployeeService(employeeRepository);
  }

  @Test
  void testUpdateEmployeeSuccess() {
    UUID employeeId = UUID.randomUUID();
    EmployeeDto employeeUpdateDto = new EmployeeDto();
    employeeUpdateDto.setRole("ROLE_ADMIN");
    employeeUpdateDto.setPosition("MANAGER");
    employeeUpdateDto.setPhone("1234567890");
    employeeUpdateDto.setName("John Doe");

    EmployeeModel employeeToUpdate = new EmployeeModel();
    Mockito.when(employeeRepository.findById(Mockito.eq(employeeId)))
        .thenReturn(Optional.of(employeeToUpdate));

    BaseDto result = updateEmployeeService.updateEmployee(employeeId, employeeUpdateDto);

    assertEquals(HttpStatus.OK, result.getResult());
    assertEquals(SuccessMessage.UPDATE_EMPLOYEE, result.getData());
  }

  @Test
  void testUpdateEmployeeNotFound() {
    UUID employeeId = UUID.randomUUID();
    EmployeeDto employeeUpdateDto = new EmployeeDto();

    Mockito.when(employeeRepository.findById(Mockito.eq(employeeId))).thenReturn(Optional.empty());

    BaseDto result = updateEmployeeService.updateEmployee(employeeId, employeeUpdateDto);

    assertEquals(HttpStatus.NOT_FOUND, result.getData());
    assertEquals(ErrorMessage.NOT_FOUND, result.getErrors());
  }

  @Test
  void testUpdateEmployeeValidationErrors() {
    UUID employeeId = UUID.randomUUID();
    EmployeeDto employeeUpdateDto = new EmployeeDto();

    EmployeeModel employeeToUpdate = new EmployeeModel();
    Mockito.when(employeeRepository.findById(Mockito.eq(employeeId)))
        .thenReturn(Optional.of(employeeToUpdate));

    List<BaseErrorDto> errors = new ArrayList<>();
    errors.add(new BaseErrorDto("role", "Função inválida"));
    Mockito.when(new UpdateEmployeeValidate().validate(any())).thenReturn(errors);

    BaseDto result = updateEmployeeService.updateEmployee(employeeId, employeeUpdateDto);

    assertEquals(HttpStatus.BAD_REQUEST, result.getErrors());
    assertEquals(BaseErrorDto.class, result.getErrors().get(0).getClass());
  }
}
