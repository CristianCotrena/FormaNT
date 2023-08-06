package com.example.locationCar.controllers;


import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.UpdateEmployeeService;
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
    private final UpdateEmployeeService updateEmployeeService;

    public EmployeeController(EmployeeService employeeService, UpdateEmployeeService updateEmployeeService) {
        this.employeeService = employeeService;
        this.updateEmployeeService = updateEmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = EmployeeService.saveEmployee(employeeModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        UUID updatedEmployeeId = UpdateEmployeeService.updateEmployee(id, employeeRecordDto);
        return ResponseEntity.ok(updatedEmployeeId);
    }
}






