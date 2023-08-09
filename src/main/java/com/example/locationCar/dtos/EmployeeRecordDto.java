package com.example.locationCar.dtos;

import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.ContractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRecordDto(

        @NotBlank String name,
        Position position,
        @NotBlank String cpfCnpj,
        @NotBlank String registry,
        @NotBlank String phone,
        ContractType contractType,
        Role role,
        @NotBlank String email
        ) {

}
