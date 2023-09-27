package com.example.locationCar.services.addressService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.SearchAddressRecordDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.repositories.AddressRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SearchAddressService {

  private final AddressRepository addressRepository;

  public SearchAddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public BaseDto findAddressById(UUID idAddress) {
    List<BaseErrorDto> errors = new ArrayList<>();
    Optional<AddressModel> address = addressRepository.findById(idAddress);

    if (address.isEmpty()) {
      errors.add(new BaseErrorDto("address", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
      return result.get();
    }
    return new ResponseSuccessBuilder<SearchAddressRecordDto>(
            HttpStatus.OK,
            new SearchAddressRecordDto(address.get()),
            SuccessMessage.ADDRESS_SUCCESS)
        .get();
  }

  public BaseDto findAddressByIdClient(UUID idClient) {
    List<BaseErrorDto> errors = new ArrayList<>();
    Optional<AddressModel> address = addressRepository.findByClientIdClient(idClient);

    if (address.isEmpty()) {
      errors.add(new BaseErrorDto("address", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
      return result.get();
    }
    return new ResponseSuccessBuilder<SearchAddressRecordDto>(
            HttpStatus.OK,
            new SearchAddressRecordDto(address.get()),
            SuccessMessage.ADDRESS_SUCCESS)
        .get();
  }

  public BaseDto findAddressByIdEmployee(UUID idEmployee) {
    List<BaseErrorDto> errors = new ArrayList<>();
    Optional<AddressModel> address = addressRepository.findByEmployeeEmployeeId(idEmployee);

    if (address.isEmpty()) {
      errors.add(new BaseErrorDto("address", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
      return result.get();
    }
    return new ResponseSuccessBuilder<SearchAddressRecordDto>(
            HttpStatus.OK,
            new SearchAddressRecordDto(address.get()),
            SuccessMessage.ADDRESS_SUCCESS)
        .get();
  }
}
