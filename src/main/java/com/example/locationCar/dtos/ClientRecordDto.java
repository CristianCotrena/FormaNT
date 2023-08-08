package com.example.locationCar.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

<<<<<<< HEAD
public record ClientRecordDto(@NotBlank String name,
                              @NotBlank String cpfCnpj,
                              @NotBlank String cnh,
                              @NotNull int age,
                              @NotBlank String telephone,
                              @NotBlank String emergencyContact,
                              @NotBlank String email,
                              @NotNull int status) {


}
=======
public record ClientRecordDto(
    @NotBlank String name,
    @NotBlank String cpfCnpj,
    @NotBlank String cnh,
    @NotNull int age,
    @NotBlank String telephone,
    @NotBlank String emergencyContact,
    @NotBlank String email)
{}
>>>>>>> f3a779f062a75f22c38cc86e8cb6fdb3979e2d70
