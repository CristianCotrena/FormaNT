package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_VEHICLE")
public class VehicleModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idVehicle;

  private String license;

  private String brand;

  private String model;

  private Integer doorNumber; // 2 ou 4

  private String color;

  private String fuel;

  private BigDecimal dailyValue;

  private BigDecimal mileage;

  private Integer status;

  private BigDecimal rating;

  public VehicleModel() {}

  public VehicleModel(
      Long idVehicle,
      String license,
      String brand,
      String model,
      int doorNumber,
      String color,
      String fuel,
      BigDecimal dailyValue,
      BigDecimal mileage,
      int status,
      BigDecimal rating) {
    this.idVehicle = idVehicle;
    this.license = license;
    this.brand = brand;
    this.model = model;
    this.doorNumber = doorNumber;
    this.color = color;
    this.fuel = fuel;
    this.dailyValue = dailyValue;
    this.mileage = mileage;
    this.status = status;
    this.rating = rating;
  }

  public Long getIdVehicle() {
    return idVehicle;
  }

  public void setIdVehicle(Long idVehicle) {
    this.idVehicle = idVehicle;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }
}
