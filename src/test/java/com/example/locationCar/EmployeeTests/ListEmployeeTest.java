package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.ListEmployeeService;
import com.example.locationCar.validate.employee.ListEmployeeValidate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

public class ListEmployeeTest {

  @Mock private EmployeeRepository employeeRepository;

  @Mock private ListEmployeeValidate listEmployeeValidate;

  private ListEmployeeService listEmployeeService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    listEmployeeService = new ListEmployeeService(employeeRepository);
  }

  @Test
  public void testListEmployees_Success() {
    Page<EmployeeModel> employeePage =
        new PageImpl<>(Collections.singletonList(new EmployeeModel()));
    when(employeeRepository.listByRoleAndPosition(any(), any(), any())).thenReturn(employeePage);

    BaseDto responseEntity = listEmployeeService.listEmployees("VENDEDOR", "VENDEDOR", "0");

    assertEquals(HttpStatus.OK.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Funcionários listados com sucesso", responseEntity.getResult().getDescription());
  }

  @Test
  public void testListEmployees_InvalidData() {
    List<BaseErrorDto> errors =
        Collections.singletonList(new BaseErrorDto("role", "Campo inválido"));

    when(listEmployeeValidate.validateParamsToSearch(anyString(), anyString(), anyString()))
        .thenReturn(errors);
    ;

    BaseDto responseEntity = listEmployeeService.listEmployees("ERRADO", "VENDEDOR", "0");

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
  }

  @Test
  public void testListEmployees_WrongPagination() {
    List<EmployeeModel> employeeList = Collections.singletonList(new EmployeeModel());
    Page<EmployeeModel> employeePage = new PageImpl<>(employeeList, PageRequest.of(0, 2), 1);
    when(employeeRepository.listByRoleAndPosition(any(), any(), any())).thenReturn(employeePage);

    BaseDto responseEntity = listEmployeeService.listEmployees(null, null, "5");

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Página informada inválida", responseEntity.getResult().getDescription());
  }
}
