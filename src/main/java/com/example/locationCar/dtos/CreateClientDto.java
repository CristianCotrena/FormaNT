package com.example.locationCar.dtos;

public class CreateClientDto {
  private String clientId;

  public CreateClientDto(String clientId) {
    this.clientId = clientId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
