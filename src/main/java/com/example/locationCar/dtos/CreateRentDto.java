package com.example.locationCar.dtos;

public class CreateRentDto {
  private String idRent;

  public CreateRentDto(String idRent) {
    this.idRent = idRent;
  }

  public String getIdRent() {
    return idRent;
  }

  public void setIdRent(String idRent) {
    this.idRent = idRent;
  }
}
