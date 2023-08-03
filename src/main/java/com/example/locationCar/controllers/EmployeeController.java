package com.example.locationCar.controllers;


import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;

import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.EmployeeServiceDelete;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@RestController
@RequestMapping("/v1/employee")
@Tag(name = "Employee", description = "Operations about Employee")
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

    @Operation(summary = "Delete Employee", description = "Delete an employee to database")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Employee deleted successfully"))
    })
    @ApiResponse(responseCode = "404", description = "Employee not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Employee not found")),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") UUID employeeId) {
        EmployeeModel employee = employeeServiceDelete.deleteEmployee(employeeId);
        return (employee == null || !employee.getEmployeeId().equals(employeeId))
                ? ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found")
                : ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }

}




