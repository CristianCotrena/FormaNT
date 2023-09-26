package com.example.locationCar.services.addressService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.client.CorreiosBuscaCepClient;
import com.example.locationCar.client.ResponseViaCep;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.AddressUpdateDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.repositories.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateAddressService {
  private final AddressRepository addressRepository;

  public UpdateAddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public BaseDto updateAddress(UUID idAddress, AddressUpdateDto addressUpdateDto) {
    Optional<AddressModel> addressBase = addressRepository.findById(idAddress);

    if (addressBase.isEmpty()) {
      List<BaseErrorDto> notFoundErrors = List.of(new BaseErrorDto("Address", ErrorMessage.NOT_FOUND));
      return new ResponseErrorBuilder(HttpStatus.NOT_FOUND, notFoundErrors).get();
    }

    AddressModel addressModel = addressBase.get();

    if (addressUpdateDto.getRoad() == null){
      ResponseViaCep responseViaCep = CorreiosBuscaCepClient.getAddressInformation(addressUpdateDto.getCep());
      if(!responseViaCep.getLogradouro().equals("")){
        addressModel.setRoad(responseViaCep.getLogradouro());
      }
    } else {
      addressModel.setRoad(addressUpdateDto.getRoad());
    }

    if(addressUpdateDto.getPublicPlace() != null){
      addressModel.setPublicPlace(addressUpdateDto.getPublicPlace());
    }
    if(addressUpdateDto.getNumber() != null) {
      addressModel.setNumber(addressUpdateDto.getNumber());
    }
    if (addressUpdateDto.getComplement() != null) {
      addressModel.setComplement(addressUpdateDto.getComplement());
    }
    if (addressUpdateDto.getCity() != null) {
      addressModel.setCity(addressUpdateDto.getCity());
    }
    if (addressUpdateDto.getState() != null) {
      addressModel.setState(addressUpdateDto.getState());
    }
    if (addressUpdateDto.getCountry() != null) {
      addressModel.setCountry(addressUpdateDto.getCountry());
    }
    if (addressUpdateDto.getCep() != null) {
      addressModel.setCep(addressUpdateDto.getCep());
    }

    addressRepository.save(addressModel);

    return new ResponseSuccessBuilder<AddressUpdateDto>(HttpStatus.OK, new AddressUpdateDto(idAddress.toString()),
            SuccessMessage.UPDATE_ADDRESS).get();
  }
}
