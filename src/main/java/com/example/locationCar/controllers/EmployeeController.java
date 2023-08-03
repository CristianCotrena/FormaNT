package com.example.locationCar.controllers;


import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;

import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.EmployeeServiceDelete;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeServiceDelete employeeServiceDelete;

    public EmployeeController(EmployeeService employeeService, EmployeeServiceDelete employeeServiceDelete) {
        this.employeeService = employeeService;
        this.employeeServiceDelete = employeeServiceDelete;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel  = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel );
                EmployeeModel savedEmployee = EmployeeService.saveEmployee(employeeModel);

        return  ResponseEntity.status(HttpStatus.CREATED).body(employeeModel );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") UUID employeeId) {
        EmployeeModel employee = employeeServiceDelete.deleteEmployee(employeeId);
        return (employee == null || !employee.getEmployeeId().equals(employeeId))
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found")
                : ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }

}




