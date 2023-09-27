package com.example.locationCar.dtos;

import com.example.locationCar.models.VehicleModel;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

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

  public VehicleRecordDto(VehicleModel vehicleModel) {
    this.idVehicle = vehicleModel.getIdVehicle();
    this.license = vehicleModel.getLicense();
    this.brand = vehicleModel.getBrand();
    this.model = vehicleModel.getModel();
    this.doorNumber = vehicleModel.getDoorNumber();
    this.color = vehicleModel.getColor();
    this.fuel = vehicleModel.getFuel();
    this.dailyValue = vehicleModel.getDailyValue();
    this.mileage = vehicleModel.getMileage();
    this.status = vehicleModel.getStatus();
    this.rating = vehicleModel.getRating();
  }

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
