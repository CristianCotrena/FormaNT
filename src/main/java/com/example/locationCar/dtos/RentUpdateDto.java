package com.example.locationCar.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentUpdateDto {
  @Schema(example = "2023-12-25T03:00:00Z")
  ZonedDateTime contractingDate;

  @Schema(example = "2024-01-10T03:00:00Z")
  ZonedDateTime returnDate;

  @Schema(example = "1")
  Integer haveInsurance;
}
