package com.example.locationCar.controllers;


import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final CreateEmployeeService createEmployeeService;
    private final UpdateEmployeeService updateEmployeeService;

    public EmployeeController(CreateEmployeeService createEmployeeService, UpdateEmployeeService updateEmployeeService) {
        this.createEmployeeService = createEmployeeService;
        this.updateEmployeeService = updateEmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = CreateEmployeeService.saveEmployee(employeeModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        UUID updatedEmployeeId = UpdateEmployeeService.updateEmployee(id, employeeRecordDto);
        return ResponseEntity.ok(updatedEmployeeId);
    }
}






