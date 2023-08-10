package com.example.locationCar.dtos;

import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.ContractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeRecordDto(

        @NotBlank @Schema(example = "Carlos") String name,
        @NotNull @Schema(example = "VENDEDOR") Position position,
        @NotBlank @Schema(example = "01630265053") String cpfCnpj,
        @NotBlank @Schema(example = "1212") String registry,
        @NotBlank @Schema(example = "(51) 3335-0435") String phone,
        @NotNull @Schema(example = "CLT") ContractType contractType,
        @NotNull @Schema(example = "VENDEDOR") Role role,
        @NotBlank @Schema(example = "marineide@gmail.com") String email
) {

}
