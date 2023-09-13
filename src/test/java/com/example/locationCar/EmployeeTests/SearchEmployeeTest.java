package com.example.locationCar.EmployeeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.SearchEmployeeService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchEmployeeTest {

  @InjectMocks private SearchEmployeeService searchEmployeeService;

  @Mock private EmployeeRepository employeeRepository;

  private EmployeeModel employeeModel;

  @BeforeEach
  void setup() {
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

  @Test
  public void testGetEmployeeById() {
    UUID id = employeeModel.getEmployeeId();

    when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeModel));

    EmployeeModel result = searchEmployeeService.employeeSearchById(id);

    assertNotNull(result);
    assertEquals(employeeModel, result);
  }

  @Test
  public void testGetEmployeeByCpfCnpj() {
    String cpfCnpj = employeeModel.getCpfCnpj();

    when(employeeRepository.findByCpfCnpj(cpfCnpj)).thenReturn(employeeModel);

    EmployeeModel result = searchEmployeeService.employeeSearchByCpfCnpj(cpfCnpj);

    assertNotNull(result);
    assertEquals(employeeModel, result);
  }

  @Test
  public void testGetEmployeeByEmail() {
    String email = employeeModel.getEmail();

    when(employeeRepository.findByEmail(email)).thenReturn(employeeModel);

    EmployeeModel result = searchEmployeeService.employeeSearchByEmail(email);

    assertNotNull(result);
    assertEquals(employeeModel, result);
  }
}
