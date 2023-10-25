package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.mock.employee.EmployeeDtoMockBuilder;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

public class CreateEmployeeTest {

  @MockBean private EmployeeRepository employeeRepository;

  @Autowired private CreateEmployeeService employeeService;

  private EmployeeDto employeeDto;

  @BeforeEach
  public void test() {
    employeeRepository = mock(EmployeeRepository.class);
    employeeService = new CreateEmployeeService(employeeRepository);

    employeeDto = EmployeeDtoMockBuilder.build();
  }

  @Test
  public void createEmployee_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    UUID validUUID = UUID.randomUUID();
    EmployeeModel savedEmployee = new EmployeeModel();
    savedEmployee.setEmployeeId(validUUID);
    when(employeeRepository.save(any(EmployeeModel.class))).thenReturn(savedEmployee);

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.CREATED.value(), result.getResult().getStatusCode());
    assertEquals("Funcionário criado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidCpf_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setCpfCnpj("000.000.000-00");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidEmail_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setEmail("xxx@");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidPhone_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setPhone("1100000");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidContractType_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setContractType("XXX");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidRole_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setRole("XXX");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_InvalidPosition_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setPosition("XXX");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_CpfAsCnpjAndContractTypeAsCnpj_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(null);
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    employeeDto.setCpfCnpj("44190639800");
    employeeDto.setContractType("CNPJ");

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void createEmployee_DuplicateCpfCnpjOrEmail_test() {
    when(employeeRepository.findByEmail(anyString())).thenReturn(new EmployeeModel());
    when(employeeRepository.findByCpfCnpj(anyString())).thenReturn(null);

    BaseDto result = employeeService.createEmployee(employeeDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }
}
