package com.example.locationCar.dtos;

import java.util.UUID;

public class RentUpdateReturnDto {
  private UUID rentId;

  public RentUpdateReturnDto(UUID rentId) {
    this.rentId = rentId;
  }

  public UUID getRentId() {
    return rentId;
  }

  public void setRentId(UUID rentId) {
    this.rentId = rentId;
  }
}
