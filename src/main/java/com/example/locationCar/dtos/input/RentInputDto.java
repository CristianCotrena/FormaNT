package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentInputDto {
  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private String idClient;

  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private String idEmployee;

  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private String idVehicle;

  @Schema(example = "1")
  private Integer haveInsurance;

  @Schema(example = "2045-03-11T00:00:00+02:00")
  private ZonedDateTime contractingDate;

  @Schema(example = "2045-05-09T00:00:00+02:00")
  private ZonedDateTime returnDate;
}
