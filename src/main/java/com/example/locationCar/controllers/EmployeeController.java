package com.example.locationCar.controllers;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.SearchEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SearchEmployeeService searchEmployeeService;

    public EmployeeController(EmployeeService employeeService, SearchEmployeeService searchEmployeeService) {
        this.employeeService = employeeService;
        this.searchEmployeeService = searchEmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = employeeService.saveEmployee(employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<EmployeeModel> getEmployee(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) String email
    ) {
        if (id != null) {
            // Buscar funcionário por ID
            EmployeeModel employee = searchEmployeeService.employeeSearchById(id);
            return ResponseEntity.ok(employee);
        } else if (cpfCnpj != null) {
            // Buscar funcionário por CPF/CNPJ
            EmployeeModel employee = searchEmployeeService.employeeSearchByCpfCnpj(cpfCnpj);
            return ResponseEntity.ok(employee);
        } else if (email != null) {
            // Buscar funcionário por e-mail
            EmployeeModel employee = searchEmployeeService.employeeSearchByEmail(email);
            return ResponseEntity.ok(employee);
        } else {
            // Nenhum parâmetro foi informado
            throw new IllegalArgumentException("Informe um ID, CPF/CNPJ ou e-mail para buscar o funcionário.");
        }
    }
}





