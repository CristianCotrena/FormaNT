package com.example.locationCar.controller;


import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.funcionarioService.EmployeeServiceDelete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioController {

    EmployeeRepository employeeRepository;
    EmployeeServiceDelete employeeServiceDelete;

    public FuncionarioController(EmployeeServiceDelete employeeServiceDelete) {
        this.employeeServiceDelete = employeeServiceDelete;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") UUID funcionarioId) {
        FuncionarioModel funcionario = employeeServiceDelete.deleteFuncionario(funcionarioId);
        return (funcionario == null || !funcionario.getIdFuncionario().equals(funcionarioId)) ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found")
                : ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }
}
