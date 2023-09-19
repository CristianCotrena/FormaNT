package com.example.locationCar.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_VEHICLE")
public class VehicleModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema (hidden = false)
    private UUID idVehicle;

    @Schema(example = "NEJ9138")
    private String license;

    @Schema(example = "BMW")
    private String brand;

    @Schema(example = "X5")
    private String model;

    @Schema(example = "2")
    private Integer doorNumber; // 2 ou 4

    @Schema(example = "red")
    private String color;

    @Schema(example = "diesel")
    private String fuel;

    @Schema(example = "150.00")
    private BigDecimal dailyValue;

    @Schema(example = "40.50")
    private BigDecimal mileage;

    @Schema(example = "1")
    private Integer status;

    @Schema(example = "5.00")
    private BigDecimal rating;

    public VehicleModel() {
    }

    public VehicleModel(UUID idVehicle, String license, String brand, String model, int doorNumber, String color, String fuel, BigDecimal dailyValue, BigDecimal mileage, int status, BigDecimal rating) {
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

    public UUID getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(UUID idVehicle) {
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
