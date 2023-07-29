package com.example.locationCar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRecordDto(
         @NotBlank String name,
         @NotBlank String cargo,
         @NotBlank String cpfCnpj,
         @NotBlank String registro,
         @NotBlank String telefone,
         @NotBlank String tipoContrato,
         @NotBlank String role,
         @NotBlank String email,
         @NotNull int status) {
}
