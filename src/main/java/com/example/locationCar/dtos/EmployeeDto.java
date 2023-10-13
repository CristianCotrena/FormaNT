package com.example.locationCar.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

  @NotBlank
  @Schema(example = "Joao Pedro")
  String name;

  @NotNull
  @Schema(example = "VENDEDOR")
  String position;

  @NotBlank
  @Schema(example = "44190639800")
  String cpfCnpj;

  @NotBlank
  @Schema(example = "1212")
  String registry;

  @NotBlank
  @Schema(example = "(51) 9999-9999")
  String phone;

  @NotNull
  @Schema(example = "CLT")
  String contractType;

  @NotNull
  @Schema(example = "VENDEDOR")
  String role;

  @NotBlank
  @Schema(example = "joaopedro@teste.com")
  String email;

  @Override
  public String toString() {
    return "EmployeeDto{"
        + "name='"
        + name
        + '\''
        + ", position="
        + position
        + ", cpfCnpj='"
        + cpfCnpj
        + '\''
        + ", registry='"
        + registry
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", contractType="
        + contractType
        + ", role="
        + role
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
