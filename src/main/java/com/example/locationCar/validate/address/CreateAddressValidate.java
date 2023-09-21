package com.example.locationCar.validate.address;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.input.AddressInputDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class CreateAddressValidate {

  public List<BaseErrorDto> validate(AddressInputDto addressInputDto) {
    List<BaseErrorDto> errors = validateRequiredFiles(addressInputDto);
    return errors.size() > 0 ? errors : validateInvalidFiles(addressInputDto, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(AddressInputDto addressInputDto) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if ((addressInputDto.getIdClient() == null || addressInputDto.getIdClient().isEmpty())
        && (addressInputDto.getIdEmployee() == null || addressInputDto.getIdEmployee().isEmpty())) {
      errors.add(new BaseErrorDto("idClientOrEmployee", ErrorMessage.EMPTY_FIELD));
    }

    if (addressInputDto.getCep() == null || addressInputDto.getCep().isEmpty()) {
      errors.add(new BaseErrorDto("cep", ErrorMessage.EMPTY_FIELD));
    }

    if (addressInputDto.getNumber() == null) {
      errors.add(new BaseErrorDto("number", ErrorMessage.EMPTY_FIELD));
    }

    if (addressInputDto.getComplement() == null || addressInputDto.getComplement().isEmpty()) {
      errors.add(new BaseErrorDto("complement", ErrorMessage.EMPTY_FIELD));
    }
    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(
      AddressInputDto addressInputDto, List<BaseErrorDto> errors) {
    if (!Pattern.compile(RegexValues.CEP).matcher(addressInputDto.getCep()).matches()) {
      errors.add(new BaseErrorDto("cep", ErrorMessage.INVALID_FIELD));
    }
    if (addressInputDto.getIdEmployee() != null && addressInputDto.getIdClient() != null) {
      errors.add(
          new BaseErrorDto(
              "idEmployee",
              ErrorMessage.EMPLOYEE_ID_OR_CLIENT_ID)); // colocar mensagem personalizada
      errors.add(
          new BaseErrorDto(
              "idClient", ErrorMessage.EMPLOYEE_ID_OR_CLIENT_ID)); // colocar mensagem personalizada
    }

    try {
      if (addressInputDto.getIdClient() != null) {
        UUID.fromString(addressInputDto.getIdClient());
      }
      if (addressInputDto.getIdEmployee() != null) {
        UUID.fromString(addressInputDto.getIdEmployee());
      }
    } catch (IllegalArgumentException e) {
      if (addressInputDto.getIdClient() != null) {
        errors.add(new BaseErrorDto("idClient", ErrorMessage.INVALID_FIELD));
      }
      if (addressInputDto.getIdEmployee() != null) {
        errors.add(new BaseErrorDto("idEmployee", ErrorMessage.INVALID_FIELD));
      }
    }

    return errors;
  }
}
