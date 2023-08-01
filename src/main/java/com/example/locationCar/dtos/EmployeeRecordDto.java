package com.example.locationCar.dtos;

import com.example.locationCar.models.enums.Cargo;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRecordDto(

        @NotBlank String nome,
        Cargo cargo,
        @NotBlank String cpfCnpj,
        @NotBlank String registro,
        @NotBlank String telefone,
        TipoContrato tipoContrato,
        Role role,
        @NotBlank String email,
        @NotNull int status) {
}
