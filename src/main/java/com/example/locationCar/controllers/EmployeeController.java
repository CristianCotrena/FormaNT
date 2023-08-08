package com.example.locationCar.controllers;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.services.funcionarioService.EmployeeService;
import com.example.locationCar.services.funcionarioService.ListEmployeeService;
import com.example.locationCar.services.funcionarioService.SearchEmployeeService;
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

    private final EmployeeService employeeService;
    private final ListEmployeeService listEmployeeService;
    private final SearchEmployeeService searchEmployeeService;

    public EmployeeController(EmployeeService employeeService,
                              ListEmployeeService listEmployeeService,
                              SearchEmployeeService searchEmployeeService) {
        this.employeeService = employeeService;
        this.listEmployeeService = listEmployeeService;
        this.searchEmployeeService = searchEmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        EmployeeModel savedEmployee = employeeService.saveEmployee(employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
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





