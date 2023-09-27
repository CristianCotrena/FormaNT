package com.example.locationCar.dtos;

import java.util.UUID;

public class DeleteAddressDto {
  private UUID idAddress;
  private Integer status;

  public DeleteAddressDto(UUID idAddress, Integer status) {
    this.idAddress = idAddress;
    this.status = status;
  }

  public UUID getIdAddress() {
    return idAddress;
  }

  public void setIdAddress(UUID idAddress) {
    this.idAddress = idAddress;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
