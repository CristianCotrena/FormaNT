package com.example.locationCar.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public class RentUpdateDto {
  @Schema(example = "2023-12-25T03:00:00")
  Date contractingDate;

  @Schema(example = "2024-01-10T03:00:00")
  Date returnDate;

  @Schema(example = "1")
  Integer haveInsurance;

  @Schema(example = "1")
  String statusVehicle;

  public Date getContractingDate() {
    return contractingDate;
  }

  public void setContractingDate(Date contractingDate) {
    this.contractingDate = contractingDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Integer getHaveInsurance() {
    return haveInsurance;
  }

  public void setHaveInsurance(Integer haveInsurance) {
    this.haveInsurance = haveInsurance;
  }

  public String getStatusVehicle() {
    return statusVehicle;
  }

  public void setStatusVehicle(String statusVehicle) {
    this.statusVehicle = statusVehicle;
  }

  @Override
  public String toString() {
    return "RentUpdateDto{"
        + "contractingDate="
        + contractingDate
        + ", returnDate="
        + returnDate
        + ", haveInsurance="
        + haveInsurance
        + '}';
  }
}
