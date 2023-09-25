package com.example.locationCar.dtos;

import com.example.locationCar.models.VehicleModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRecordDto {

  private UUID idVehicle;

  private String license;

  private String brand;

  private String model;

  private Integer doorNumber;

  private String color;

  private String fuel;

  private BigDecimal dailyValue;

  private BigDecimal mileage;

  private Integer status;

  private BigDecimal rating;

  public static VehicleRecordDto fromVehicleModel(VehicleModel vehicleModel) {
    return VehicleRecordDto.builder()
            .idVehicle(vehicleModel.getIdVehicle())
            .license(vehicleModel.getLicense())
            .brand(vehicleModel.getBrand())
            .model(vehicleModel.getModel())
            .doorNumber(vehicleModel.getDoorNumber())
            .color(vehicleModel.getColor())
            .fuel(vehicleModel.getFuel())
            .dailyValue(vehicleModel.getDailyValue())
            .mileage(vehicleModel.getMileage())
            .status(vehicleModel.getStatus())
            .rating(vehicleModel.getRating())
            .build();
  }
}
