package com.example.locationCar.controller;


import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;

import com.example.locationCar.services.funcionarioService.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/funcionario")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel  = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel );
                EmployeeModel savedFuncionario = EmployeeService.saveFuncionario(employeeModel );

        return  ResponseEntity.status(HttpStatus.CREATED).body(employeeModel );
    }

}




