package com.example.locationCar.mock.rent;

import com.example.locationCar.dtos.RentUpdateDto;
import java.time.ZonedDateTime;

public class RentUpdateDtoMockBuilder {

  public static RentUpdateDto build() {
    RentUpdateDto rentUpdateDto =
        RentUpdateDto.builder()
            .contractingDate(ZonedDateTime.parse("2023-12-25T03:00:00Z"))
            .returnDate(ZonedDateTime.parse("2024-01-15T03:00:00Z"))
            .haveInsurance(1)
            .build();

    return rentUpdateDto;
  }
}
