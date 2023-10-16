package com.example.locationCar.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_VEHICLE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(hidden = false)
  private UUID idVehicle;

  @Schema(example = "NEJ9138")
  private String license;

  @Schema(example = "BMW")
  private String brand;

  @Schema(example = "X5")
  private String model;

  @Schema(example = "2")
  private Integer doorNumber; // 2 ou 4

  @Schema(example = "red")
  private String color;

  @Schema(example = "diesel")
  private String fuel;

  @Schema(example = "150.00")
  private BigDecimal dailyValue;

  @Schema(example = "40.50")
  private BigDecimal mileage;

  @Schema(example = "1")
  private Integer status;

  @Schema(example = "5.00")
  private BigDecimal rating;
}
