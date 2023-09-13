package com.example.locationCar.dtos;

public class CreateEmployeeDto {

  private String employeeId;

  public CreateEmployeeDto(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }
}
