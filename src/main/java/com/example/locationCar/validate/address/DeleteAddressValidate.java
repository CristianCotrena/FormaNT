package com.example.locationCar.validate.address;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeleteAddressValidate {

  public List<BaseErrorDto> validateParams(String idAddress, String idEmployee, String idClient) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (idAddress.isEmpty() && idEmployee.isEmpty() && idClient.isEmpty()) {
      errors.add(new BaseErrorDto("idAddress", ErrorMessage.EMPTY_FIELD));
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.EMPTY_FIELD));
      errors.add(new BaseErrorDto("idClient", ErrorMessage.EMPTY_FIELD));

      return errors;
    }

    if (!idAddress.isEmpty() && !idEmployee.isEmpty() && !idClient.isEmpty()) {
      errors.add(new BaseErrorDto("idAddress", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      errors.add(new BaseErrorDto("idClient", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      return errors;
    }

    if (!idAddress.isEmpty() && !idEmployee.isEmpty()) {
      errors.add(new BaseErrorDto("idAddress", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      return errors;
    }

    if (!idAddress.isEmpty() && !idClient.isEmpty()) {
      errors.add(new BaseErrorDto("idAddress", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      errors.add(new BaseErrorDto("idClient", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      return errors;
    }

    if (!idEmployee.isEmpty() && !idClient.isEmpty()) {
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      errors.add(new BaseErrorDto("idClient", ErrorMessage.ONLY_POSSIBLE_TO_SEND_ONE_FIELD));
      return errors;
    }

    return errors;
  }

  public boolean validateUUID(String idAddress, String idEmployee, String idClient) {
    try {
      if (!idAddress.isEmpty()) {
        UUID.fromString(idAddress);
      }

      if (!idEmployee.isEmpty()) {
        UUID.fromString(idEmployee);
      }

      if (!idClient.isEmpty()) {
        UUID.fromString(idClient);
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
