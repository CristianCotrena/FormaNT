package com.example.locationCar.mock.vehicle;

import com.example.locationCar.models.VehicleModel;
import java.math.BigDecimal;
import java.util.UUID;

public class VehicleMockBuilder {

  public static VehicleModel build() {
    VehicleModel vehicleModel =
        VehicleModel.builder()
            .idVehicle(UUID.randomUUID())
            .license("NEJ9138")
            .brand("BMW")
            .model("X5")
            .doorNumber(2)
            .color("red")
            .fuel("diesel")
            .dailyValue(BigDecimal.valueOf(150.00))
            .mileage(BigDecimal.valueOf(40.50))
            .rating(BigDecimal.valueOf(5.00))
            .status(1)
            .build();

    return vehicleModel;
  }
}
