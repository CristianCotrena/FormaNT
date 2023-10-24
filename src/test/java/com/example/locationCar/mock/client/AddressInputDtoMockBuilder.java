package com.example.locationCar.mock.client;

import com.example.locationCar.dtos.input.AddressInputDto;

public class AddressInputDtoMockBuilder {

  public static AddressInputDto build() {
    AddressInputDto addressInputDto =
        AddressInputDto.builder().cep("74840300").complement("Casa de Esquina").number(12).build();

    return addressInputDto;
  }
}
