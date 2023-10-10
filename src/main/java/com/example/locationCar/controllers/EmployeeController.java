package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.dtos.EmployeeUpdateDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import com.example.locationCar.services.employeeService.DeleteEmployeeService;
import com.example.locationCar.services.employeeService.ListEmployeeService;
import com.example.locationCar.services.employeeService.SearchEmployeeService;
import com.example.locationCar.services.employeeService.UpdateEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employee")
@Tag(name = "Employee", description = "Operations about Employee")
@AllArgsConstructor
public class EmployeeController {

  private final ListEmployeeService listEmployeeService;
  private final SearchEmployeeService searchEmployeeService;
  private final CreateEmployeeService createEmployeeService;
  private final UpdateEmployeeService updateEmployeeService;
  private final DeleteEmployeeService deleteEmployeeService;

  @Operation(summary = "Create employee", description = "Add an employee to database")
  @ApiResponse(
      responseCode = "201",
      description = "Created",
      content = {
        @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "text/plain",
            schema =
                @Schema(
                    type = "string",
                    example = "Já existe um employee cadastrado com este e-mail")),
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "CPF ou CNPJ inválido")),
      })
  @PostMapping
  public ResponseEntity<BaseDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
    System.out.println("Received employeeDto: " + employeeDto);
    BaseDto baseDto = createEmployeeService.createEmployee(employeeDto);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(
      summary = "Atualizar funcionário",
      description = "Atualizar um funcionário existente no banco de dados")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Dados inválidos",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Funcionário não encontrado")),
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Dados de entrada inválidos"))
      })
  @PutMapping("/{id}")
  public ResponseEntity<?> updateEmployee(
      @PathVariable UUID id, @RequestBody EmployeeUpdateDto employeeDto) {
    BaseDto updatedEmployeeId = updateEmployeeService.updateEmployee(id, employeeDto, true);
    return ResponseEntity.ok(updatedEmployeeId);
  }

  @Operation(summary = "List employees", description = "List employees")
  @ApiResponse(
      responseCode = "200",
      description = "Found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EmployeeModel.class))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Funcionários não encontrados.")),
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "text/plain",
            schema =
                @Schema(type = "string", example = "Erro na solicitação de listar funcionários.")),
      })
  @GetMapping("/list")
  public ResponseEntity<BaseDto> listEmployees(
      @RequestParam(required = false) String role,
      @RequestParam(required = false) String position,
      @RequestParam(required = false) String page) {
    BaseDto baseDto = listEmployeeService.listEmployees(role, position, page);

    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(summary = "Search Employee", description = "Search an employee from database")
  @ApiResponse(
      responseCode = "404",
      description = "Employee not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema =
                @Schema(type = "string", example = "Funcionário não encontrado pelo ID informado."))
      })
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid")),
      })
  @GetMapping
  public ResponseEntity<Object> getEmployee(
      @RequestParam(required = false) UUID id,
      @RequestParam(required = false) String cpfCnpj,
      @RequestParam(required = false) String email) {
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
        return ResponseEntity.badRequest()
            .body("Informe um ID, CPF/CNPJ ou e-mail válido para buscar o funcionário.");
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
          .body("Informe um ID, CPF/CNPJ ou e-mail válido para buscar o funcionário.");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Erro ao processar a requisição.");
    }
  }

  @Operation(summary = "Delete Employee", description = "Delete an employee to database")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Employee deleted successfully"))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Employee not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Employee not found")),
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") UUID employeeId) {
    EmployeeModel employee = deleteEmployeeService.deleteEmployee(employeeId);
    return (employee == null || !employee.getEmployeeId().equals(employeeId))
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found")
        : ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
  }
}
