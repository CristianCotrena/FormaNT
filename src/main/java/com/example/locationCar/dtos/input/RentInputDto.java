package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.UUID;


public class RentInputDto {
    @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
    private String idClient;
    @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
    private String idEmployee;
    @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
    private String idVehicle;
    @Schema(example = "1")
    private Integer haveInsurance;
    @Schema(example = "2045-03-11T00:00:00+02:00")
    private ZonedDateTime contractingDate;
    @Schema(example = "2045-05-09T00:00:00+02:00")
    private ZonedDateTime returnDate;

    public RentInputDto() {
    }

    public RentInputDto(String idClient, String idEmployee, String idVehicle, Integer haveInsurance, ZonedDateTime contractingDate, ZonedDateTime returnDate) {
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.idVehicle = idVehicle;
        this.haveInsurance = haveInsurance;
        this.contractingDate = contractingDate;
        this.returnDate = returnDate;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Integer getHaveInsurance() {
        return haveInsurance;
    }

    public void setHaveInsurance(Integer haveInsurance) {
        this.haveInsurance = haveInsurance;
    }

    public ZonedDateTime getContractingDate() {
        return contractingDate;
    }

    public void setContractingDate(ZonedDateTime contractingDate) {
        this.contractingDate = contractingDate;
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(ZonedDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
