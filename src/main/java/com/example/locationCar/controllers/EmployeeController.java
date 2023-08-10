package com.example.locationCar.controllers;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import com.example.locationCar.services.employeeService.SearchEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final SearchEmployeeService searchEmployeeService;

    public EmployeeController(CreateEmployeeService createEmployeeService, UpdateEmployeeService updateEmployeeService, SearchEmployeeService searchEmployeeService) {
        this.createEmployeeService = createEmployeeService;
        this.updateEmployeeService = updateEmployeeService;
        this.searchEmployeeService = searchEmployeeService;
    
    }



    @Operation(summary = "Create employee", description = "Add an employee to database")
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Já existe um employee cadastrado com este e-mail")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "CPF ou CNPJ inválido")),

    })
    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = createEmployeeService.saveEmployee(employeeModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee)  ;
    }

    @Operation(summary = "Atualizar funcionário", description = "Atualizar um funcionário existente no banco de dados")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Funcionário não encontrado")),
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Dados de entrada inválidos"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        UUID updatedEmployeeId = updateEmployeeService.updateEmployee(id, employeeRecordDto);
        return ResponseEntity.ok(updatedEmployeeId);
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




