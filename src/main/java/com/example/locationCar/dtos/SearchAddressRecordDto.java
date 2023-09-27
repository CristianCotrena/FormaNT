package com.example.locationCar.dtos;

import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;

public class SearchAddressRecordDto {

  private UUID idAddress;

  private String publicPlace;
  private int number;
  private String complement;
  private String city;
  private String state;
  private String country;
  private String cep;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private ClientModel client;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private EmployeeModel employee;

  public SearchAddressRecordDto() {}

  public SearchAddressRecordDto(AddressModel addressModel) {
    this.idAddress = addressModel.getIdAddress();
    this.publicPlace = addressModel.getPublicPlace();
    this.number = addressModel.getNumber();
    this.complement = addressModel.getComplement();
    this.city = addressModel.getCity();
    this.state = addressModel.getState();
    this.country = addressModel.getCountry();
    this.cep = addressModel.getCep();
    this.client = addressModel.getClient();
    this.employee = addressModel.getEmployee();
  }

  public UUID getIdAddress() {
    return idAddress;
  }

  public void setIdAddress(UUID idAddress) {
    this.idAddress = idAddress;
  }

  public String getPublicPlace() {
    return publicPlace;
  }

  public void setPublicPlace(String publicPlace) {
    this.publicPlace = publicPlace;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public ClientModel getClient() {
    return client;
  }

  public void setClient(ClientModel client) {
    this.client = client;
  }

  public EmployeeModel getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeModel employee) {
    this.employee = employee;
  }
}
