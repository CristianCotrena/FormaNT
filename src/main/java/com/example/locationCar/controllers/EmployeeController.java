package com.example.locationCar.controllers;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.EmployeeServiceDelete;
import com.example.locationCar.services.funcionarioService.SearchEmployeeService;
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

    private final SearchEmployeeService searchEmployeeService;

    public EmployeeController(EmployeeService employeeService, SearchEmployeeService searchEmployeeService, EmployeeServiceDelete employeeServiceDelete) {
        this.employeeService = employeeService;
        this.searchEmployeeService = searchEmployeeService;
        this.employeeServiceDelete = employeeServiceDelete;

    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = employeeService.saveEmployee(employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
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


    @Operation(summary = "Search Employee", description = "Search an employee from database")
    @ApiResponse(responseCode = "404", description = "Employee not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Funcionário não encontrado pelo ID informado."))
    })
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid")),
    })
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





