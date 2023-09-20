package com.example.locationCar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDto(
    @NotBlank String name,
    @NotBlank String cpfCnpj,
    @NotBlank String cnh,
    @NotNull int age,
    @NotBlank String telephone,
    @NotBlank String emergencyContact,
    @NotBlank String email) {}
