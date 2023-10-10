package com.example.locationCar.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;

public class RentUpdateDto {
  @Schema(example = "2023-12-25T03:00:00Z")
  ZonedDateTime contractingDate;

  @Schema(example = "2024-01-10T03:00:00Z")
  ZonedDateTime returnDate;

  @Schema(example = "1")
  Integer haveInsurance;

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

  public Integer getHaveInsurance() {
    return haveInsurance;
  }

  public void setHaveInsurance(Integer haveInsurance) {
    this.haveInsurance = haveInsurance;
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
