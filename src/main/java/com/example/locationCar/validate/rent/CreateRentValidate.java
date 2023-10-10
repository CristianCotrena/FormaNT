package com.example.locationCar.validate.rent;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.dtos.input.RentInputDto;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateRentValidate {

  public List<BaseErrorDto> validate(RentInputDto rentInputDto) {
    List<BaseErrorDto> errors = validateRequiredFiles(rentInputDto);
    return errors.size() > 0 ? errors : validateInvalidFiles(rentInputDto, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(RentInputDto rentInputDto) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (rentInputDto.getIdClient() == null) {
      errors.add(new BaseErrorDto("idClient", ErrorMessage.EMPTY_FIELD));
    }
    if (rentInputDto.getIdEmployee() == null) {
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.EMPTY_FIELD));
    }

    if (rentInputDto.getIdVehicle() == null) {
      errors.add(new BaseErrorDto("idVehicle", ErrorMessage.EMPTY_FIELD));
    }

    if (rentInputDto.getHaveInsurance() == null) {
      errors.add(new BaseErrorDto("haveInsurance", ErrorMessage.EMPTY_FIELD));
    }

    if (rentInputDto.getContractingDate() == null) {
      errors.add(new BaseErrorDto("contractingDate", ErrorMessage.EMPTY_FIELD));
    }

    if (rentInputDto.getReturnDate() == null) {
      errors.add(new BaseErrorDto("returnDate", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(
      RentInputDto rentInputDto, List<BaseErrorDto> errors) {
    if (!rentInputDto.getContractingDate().isBefore(rentInputDto.getReturnDate())) {
      errors.add(new BaseErrorDto("contractingDate", ErrorMessage.MUST_BE_LESS_THAN_RETURN_DATE));
      errors.add(
          new BaseErrorDto("returnDate", ErrorMessage.MUST_BE_GREATER_THAN_CONTRACTING_DATE));
    }

    if (rentInputDto.getContractingDate().isBefore(ZonedDateTime.now())) {
      errors.add(new BaseErrorDto("contractingDate", ErrorMessage.MUSTNT_BE_IN_THE_PAST));
    }

    if (rentInputDto.getHaveInsurance() != 0 && rentInputDto.getHaveInsurance() != 1) {
      errors.add(new BaseErrorDto("haveInsurance", ErrorMessage.MUST_BE_ZERO_OR_ONE));
    }

    try {
      UUID.fromString(rentInputDto.getIdVehicle());
    } catch (IllegalArgumentException e) {
      errors.add(new BaseErrorDto("idVehicle", ErrorMessage.INVALID_FIELD));
    }

    try {
      UUID.fromString(rentInputDto.getIdClient());
    } catch (IllegalArgumentException e) {
      errors.add(new BaseErrorDto("idClient", ErrorMessage.INVALID_FIELD));
    }

    try {
      UUID.fromString(rentInputDto.getIdEmployee());
    } catch (IllegalArgumentException e) {
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.INVALID_FIELD));
    }

    return errors;
  }
}
