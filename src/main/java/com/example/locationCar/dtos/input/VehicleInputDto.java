package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInputDto {

  @Schema(example = "BRA2E18")
  private String license;

  @Schema(example = "tesla")
  private String brand;

  @Schema(example = "s")
  private String model;

  @Schema(example = "4")
  private Integer doorNumber; // 2 ou 4

  @Schema(example = "black")
  private String color;

  @Schema(example = "gasoline")
  private String fuel;

  @Schema(example = "43.3")
  private BigDecimal dailyValue;

  @Schema(example = "2500.5")
  private BigDecimal mileage;

  @Schema(example = "2.5")
  private BigDecimal rating;
}
