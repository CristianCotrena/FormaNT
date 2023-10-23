package com.example.locationCar.mock.rent;

import com.example.locationCar.dtos.input.RentInputDto;
import java.time.ZonedDateTime;

public class RentInputDtoMockBuilder {

  public static RentInputDto build() {
    RentInputDto rentInputDto =
        RentInputDto.builder()
            .idClient("f330b8fb-4e38-44f5-a096-11fbc43d07d3")
            .idEmployee("0ca1f321-06ad-4845-845d-4c09a7a1b48e")
            .idVehicle("cf28f96b-0fb0-4af9-9102-de2aa9e6cda7")
            .contractingDate(ZonedDateTime.now())
            .returnDate(ZonedDateTime.now().plusDays(30))
            .haveInsurance(0)
            .build();

    return rentInputDto;
  }
}
