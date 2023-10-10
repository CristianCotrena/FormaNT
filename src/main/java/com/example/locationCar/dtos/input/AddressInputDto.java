package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressInputDto {
  @Schema(example = "cd065886-1640-4190-9c5d-a5432cf93767")
  private String idClient;

  @Schema(example = "cd065886-1640-4190-9c5d-a5432cf93767")
  private String idEmployee;

  @Schema(example = "74840300")
  private String cep;

  @Schema(example = "2")
  private Integer number;

  @Schema(example = "Casa de Esquina, perto do mercado Moreira;")
  private String complement;
}
