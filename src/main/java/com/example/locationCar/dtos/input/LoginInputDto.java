package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInputDto {
  @Schema(example = "test@gmail.com.br")
  private String email;

  @Schema(example = "Passw0rd")
  private String password;

  @Schema(example = "13bd5e93-25d8-4582-b550-6c57bca59d7f")
  private String idClient;

  @Schema(example = "09da00e6-0c71-4e84-965f-b95d67199682")
  private String employeeId;

}
