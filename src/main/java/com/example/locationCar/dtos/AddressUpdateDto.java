package com.example.locationCar.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class AddressUpdateDto {

  @Schema(example = "99edbcb8-f974-479a-a3bf-f3ab8da7b9af")
  String addressId;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "Sim ou Não")
  String publicPlace;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "Bage")
  String road;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "457")
  Integer number;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "Loja")
  String complement;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "Cachoeirinha")
  String city;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "RS")
  String state;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "Brasil")
  String country;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(example = "94945-000")
  String cep;

  public AddressUpdateDto() {}

  public AddressUpdateDto(
          UUID idAddress,
          String publicPlace,
          String road,
          Integer number,
          String complement,
          String city,
          String state,
          String country,
          String cep)
        {
    this.addressId = addressId;
    this.publicPlace = publicPlace;
    this.road = road;
    this.number = number;
    this.complement = complement;
    this.city = city;
    this.state = state;
    this.country = country;
    this.cep = cep;
  }

  public AddressUpdateDto(String addressId) {
    this.addressId = addressId;
  }

  public String getAddressId() {
    return addressId;
  }
  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }
  public String getPublicPlace() {
    return publicPlace;
}
  public void setPublicPlace(String publicPlace) {
    this.publicPlace = publicPlace;
  }
  public String getRoad() {
    return road;
  }
  public void setRoad(String road) {
    this.road = road;
  }
  public Integer getNumber() {
    return  number;
  }
  public void setNumber(Integer number){
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
}

