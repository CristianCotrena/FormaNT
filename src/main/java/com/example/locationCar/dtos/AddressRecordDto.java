package com.example.locationCar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRecordDto(
    @NotBlank String publicPlace,
    @NotNull int number,
    @NotBlank String complement,
    @NotBlank String city,
    @NotBlank String state,
    @NotBlank String country,
    @NotBlank String cep,
    @NotNull int status) {}
