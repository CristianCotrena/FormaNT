package com.example.locationCar.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDto {

  @Schema(example = "Joao Pedro")
  String name;

  @Schema(example = "VENDEDOR")
  String position;

  @Schema(example = "44190639800")
  String cpfCnpj;

  @Schema(example = "1212")
  String registry;

  @Schema(example = "(51) 9999-9999")
  String phone;

  @Schema(example = "CLT")
  String contractType;

  @Schema(example = "VENDEDOR")
  String role;

  @Schema(example = "joaopedro@teste.com")
  String email;

  @Schema(example = "1")
  Integer status;

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
