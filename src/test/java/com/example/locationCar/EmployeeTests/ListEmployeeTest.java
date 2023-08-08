package com.example.locationCar.EmployeeTests;

import com.example.locationCar.controllers.EmployeeController;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.services.funcionarioService.ListEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListEmployeeTest {
    @Mock
    private ListEmployeeService listEmployeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private List<EmployeeModel> employees;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        employees = new ArrayList<>();
        employees.add(new EmployeeModel(UUID.fromString("03126285-ff54-4b26-86d6-fccd155cc4fc"), "Funcionário 1", Position.VENDEDOR, "11111111111", "123456", "1111111111", ContractType.CLT, Role.ADMINISTRADOR, "employee1@gmail.com", 1));
        employees.add(new EmployeeModel(UUID.fromString("03126285-ff54-4b26-86d6-fccd155cc4fd"), "Funcionário 2", Position.VENDEDOR, "22222222222", "1234567", "2222222222", ContractType.CNPJ, Role.VENDEDOR,"employee2@gmail.com", 1));
        employees.add(new EmployeeModel(UUID.fromString("03126285-ff54-4b26-86d6-fccd155cc4fe"), "Funcionário 3", Position.ESTOQUISTA, "33333333333", "12345678", "3333333333", ContractType.CLT, Role.VENDEDOR,"employee3@gmail.com", 1));
    }

    @Test
    public void testListEmployees_AllEmployees() {
        when(listEmployeeService.listEmployees(null, null, null)).thenReturn(new PageImpl<>(employees));

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(null, null, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employees, ((Page<EmployeeModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListEmployees_RoleFilter() {
        Role roleFilter = Role.VENDEDOR;

        List<EmployeeModel> filteredEmployees = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getRole() == roleFilter) {
                filteredEmployees.add(employee);
            }
        }

        when(listEmployeeService.listEmployees(roleFilter, null, null)).thenReturn(new PageImpl<>(filteredEmployees));

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(roleFilter, null, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filteredEmployees, ((Page<EmployeeModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListEmployees_PositionFilter() {
        Position positionFilter = Position.ESTOQUISTA;

        List<EmployeeModel> filteredEmployees = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getPosition() == positionFilter) {
                filteredEmployees.add(employee);
            }
        }

        when(listEmployeeService.listEmployees(null, positionFilter, null)).thenReturn(new PageImpl<>(filteredEmployees));

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(null, positionFilter, null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filteredEmployees, ((Page<EmployeeModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListEmployees_PositionFilterWithRoleAndPage() {
        Position position = Position.VENDEDOR;
        Role role = Role.VENDEDOR;
        int page = 1;
        List<EmployeeModel> filteredEmployees = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            if (employee.getPosition() == position && employee.getRole() == role) {
                filteredEmployees.add(employee);
            }
        }

        when(listEmployeeService.listEmployees(role, position, page)).thenReturn(new PageImpl<>(filteredEmployees));

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(role, position, page);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filteredEmployees, ((Page<EmployeeModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListEmployees_Pagination() {
        int page = 1;
        int pageSize = 2;
        List<EmployeeModel> paginatedEmployees = employees.subList(page * pageSize, Math.min((page + 1) * pageSize, employees.size()));

        when(listEmployeeService.listEmployees(null, null, page)).thenReturn(new PageImpl<>(paginatedEmployees));

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(null, null, page);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paginatedEmployees, ((Page<EmployeeModel>) responseEntity.getBody()).getContent());
    }

    @Test
    public void testListEmployees_NotFound() {
        when(listEmployeeService.listEmployees(null, null, null)).thenReturn(Page.empty());

        ResponseEntity<Object> responseEntity = employeeController.listEmployees(null, null, null);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Funcionários não encontrados.", responseEntity.getBody());
    }
}
