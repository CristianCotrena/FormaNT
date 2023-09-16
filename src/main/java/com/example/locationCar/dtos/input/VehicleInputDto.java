package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class VehicleInputDto {

  @Schema(example = "BRA2E18")
  private String license;

  @Schema(example = "tesla")
  private String brand;

  @Schema(example = "s")
  private String model;

  @Schema(example = "4")
  private Integer doorNumber; // 2 ou 4

  @Schema(example = "black")
  private String color;

  @Schema(example = "gasoline")
  private String fuel;

  @Schema(example = "43.3")
  private BigDecimal dailyValue;

  @Schema(example = "2500.5")
  private BigDecimal mileage;

  @Schema(example = "2.5")
  private BigDecimal rating;

  public VehicleInputDto() {}

  public VehicleInputDto(
      String license,
      String brand,
      String model,
      Integer doorNumber,
      String color,
      String fuel,
      BigDecimal dailyValue,
      BigDecimal mileage,
      BigDecimal rating) {
    this.license = license;
    this.brand = brand;
    this.model = model;
    this.doorNumber = doorNumber;
    this.color = color;
    this.fuel = fuel;
    this.dailyValue = dailyValue;
    this.mileage = mileage;
    this.rating = rating;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getDoorNumber() {
    return doorNumber;
  }

  public void setDoorNumber(Integer doorNumber) {
    this.doorNumber = doorNumber;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getFuel() {
    return fuel;
  }

  public void setFuel(String fuel) {
    this.fuel = fuel;
  }

  public BigDecimal getDailyValue() {
    return dailyValue;
  }

  public void setDailyValue(BigDecimal dailyValue) {
    this.dailyValue = dailyValue;
  }

  public BigDecimal getMileage() {
    return mileage;
  }

  public void setMileage(BigDecimal mileage) {
    this.mileage = mileage;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }
}
