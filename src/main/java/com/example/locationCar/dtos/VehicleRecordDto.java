package com.example.locationCar.dtos;

import com.example.locationCar.models.VehicleModel;

import java.math.BigDecimal;
import java.util.UUID;

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

    public VehicleRecordDto() {
    }

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