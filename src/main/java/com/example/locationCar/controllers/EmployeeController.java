package com.example.locationCar.controllers;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;

import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.services.funcionarioService.ListEmployeeService;

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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/employee")
@Tag(name = "Employee", description = "Operations about employee")
public class EmployeeController {

    private final ListEmployeeService listEmployeeService;
    private final SearchEmployeeService searchEmployeeService;
    private final CreateEmployeeService createEmployeeService;
    private final UpdateEmployeeService updateEmployeeService;

    public EmployeeController(CreateEmployeeService createEmployeeService, UpdateEmployeeService updateEmployeeService, SearchEmployeeService searchEmployeeService, ListEmployeeService listEmployeeService) {
        this.createEmployeeService = createEmployeeService;
        this.updateEmployeeService = updateEmployeeService;
        this.searchEmployeeService = searchEmployeeService;
        this.listEmployeeService = listEmployeeService;
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

    @Operation(summary = "List employees", description = "List employees")
    @ApiResponse(responseCode = "200", description = "Found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeModel.class))
    })
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Funcionários não encontrados.")),
    })
    @ApiResponse(responseCode = "400", description = "Invalid data", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Erro na solicitação de listar funcionários.")),
    })
    @GetMapping("/list")
    public ResponseEntity<Object> listEmployees(@RequestParam(required = false) Role role,
                                              @RequestParam(required = false) Position position,
                                                @RequestParam(required = false) Integer page) {
        try {
            Page<EmployeeModel> employees = listEmployeeService.listEmployees(role, position, page);

            if(employees.isEmpty()) return new ResponseEntity<>("Funcionários não encontrados.", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getEmployee(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) String email
    ) {
        try {
            if (id != null) {
                EmployeeModel employee = searchEmployeeService.employeeSearchById(id);
                return ResponseEntity.ok(employee);
            } else if (cpfCnpj != null) {
                EmployeeModel employee = searchEmployeeService.employeeSearchByCpfCnpj(cpfCnpj);
                return ResponseEntity.ok(employee);
            } else if (email != null) {
                EmployeeModel employee = searchEmployeeService.employeeSearchByEmail(email);
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.badRequest().body("Informe um ID, CPF/CNPJ ou e-mail válido para buscar o funcionário.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Informe um ID, CPF/CNPJ ou e-mail válido para buscar o funcionário.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }
}




