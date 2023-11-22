package com.example.locationCar.dtos;

public class DeleteRentDto {

  private String idRent;

  public DeleteRentDto(String idRent) {
    this.idRent = idRent;
  }

  public String getIdRent() {
    return idRent;
  }

  public void setIdRent(String idRent) {
    this.idRent = idRent;
  }
}
