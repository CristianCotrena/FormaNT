package com.example.locationCar.mock.vehicle;

import com.example.locationCar.dtos.input.VehicleInputDto;
import java.math.BigDecimal;

public class VehicleInputDtoMockBuilder {

  public static VehicleInputDto build() {
    VehicleInputDto vehicleInputDto =
        VehicleInputDto.builder()
            .brand("Tesla")
            .color("Black")
            .fuel("Eletric")
            .model("s")
            .license("BRA2E25")
            .doorNumber(4)
            .mileage(BigDecimal.valueOf(300.5))
            .dailyValue(BigDecimal.valueOf(50.3))
            .rating(BigDecimal.valueOf(4.5))
            .build();

    return vehicleInputDto;
  }
}
