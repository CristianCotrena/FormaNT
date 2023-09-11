package com.example.locationCar.dtos;

public class CreateVehicleDto {
  private String vehicleId;

  public CreateVehicleDto(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }
}
