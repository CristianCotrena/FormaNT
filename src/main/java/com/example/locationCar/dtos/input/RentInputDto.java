package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.UUID;

public class RentInputDto {
  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private UUID idClient;

  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private UUID idEmployee;

  @Schema(example = "f330b8fb-4e38-44f5-a096-11fbc43d07d3")
  private UUID idVehicle;

  @Schema(example = "1")
  private Integer haveInsurance;

  @Schema(example = "1")
  private Integer status;

  @Schema(example = "2045-03-11T00:00:00+02:00")
  private ZonedDateTime contractingDate;

  @Schema(example = "2045-05-09T00:00:00+02:00")
  private ZonedDateTime returnDate;

  public RentInputDto() {}

  public RentInputDto(
      UUID idClient,
      UUID idEmployee,
      UUID idVehicle,
      Integer haveInsurance,
      Integer status,
      ZonedDateTime contractingDate,
      ZonedDateTime returnDate) {
    this.idClient = idClient;
    this.idEmployee = idEmployee;
    this.idVehicle = idVehicle;
    this.haveInsurance = haveInsurance;
    this.status = status;
    this.contractingDate = contractingDate;
    this.returnDate = returnDate;
  }

  public UUID getIdClient() {
    return idClient;
  }

  public void setIdClient(UUID idClient) {
    this.idClient = idClient;
  }

  public UUID getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(UUID idEmployee) {
    this.idEmployee = idEmployee;
  }

  public UUID getIdVehicle() {
    return idVehicle;
  }

  public void setIdVehicle(UUID idVehicle) {
    this.idVehicle = idVehicle;
  }

  public Integer getHaveInsurance() {
    return haveInsurance;
  }

  public void setHaveInsurance(Integer haveInsurance) {
    this.haveInsurance = haveInsurance;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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
