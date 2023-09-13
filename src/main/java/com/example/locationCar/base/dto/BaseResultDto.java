package com.example.locationCar.base.dto;

public class BaseResultDto {
  private Integer statusCode;
  private String description;

  public BaseResultDto(Integer statusCode, String description) {
    this.statusCode = statusCode;
    this.description = description;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
