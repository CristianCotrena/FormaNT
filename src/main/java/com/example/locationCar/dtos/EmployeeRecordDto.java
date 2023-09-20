package com.example.locationCar.dtos;

import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRecordDto(
    @NotBlank @Schema(example = "Joao Pedro") String name,
    @NotNull @Schema(example = "VENDEDOR") Position position,
    @NotBlank @Schema(example = "44190639800") String cpfCnpj,
    @NotBlank @Schema(example = "1212") String registry,
    @NotBlank @Schema(example = "(51) 9999-9999") String phone,
    @NotNull @Schema(example = "CLT") ContractType contractType,
    @NotNull @Schema(example = "VENDEDOR") Role role,
    @NotBlank @Schema(example = "joaopedro@teste.com") String email) {}
