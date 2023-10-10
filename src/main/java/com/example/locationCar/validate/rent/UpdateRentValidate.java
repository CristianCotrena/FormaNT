package com.example.locationCar.validate.rent;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.models.RentModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UpdateRentValidate {

  public List<BaseErrorDto> validateUpdate(RentUpdateDto rentToUpdate, RentModel rentBase) {
    List<BaseErrorDto> errors = new ArrayList<>();

    LocalDate formattedContractingDateToUpdate =
        rentToUpdate.getContractingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate formattedContractingDateBase =
        rentBase.getContractingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    if (!formattedContractingDateToUpdate.equals(formattedContractingDateBase)) {
      errors.add(new BaseErrorDto("contractingDate", ErrorMessage.NEGATIVE_UPDATE));
    }

    if (rentToUpdate.getHaveInsurance() != rentBase.getHaveInsurance()) {
      errors.add(new BaseErrorDto("haveInsurance", ErrorMessage.NEGATIVE_UPDATE));
    }

    return errors;
  }

  public List<BaseErrorDto> validateParams(String rentId, RentUpdateDto rentToUpdate) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (rentId.isEmpty()) {
      errors.add(new BaseErrorDto("id", ErrorMessage.EMPTY_FIELD));
    }

    if (rentToUpdate.getContractingDate() == null) {
      errors.add(new BaseErrorDto("contractingDate", ErrorMessage.EMPTY_FIELD));
    }

    if (rentToUpdate.getReturnDate() == null) {
      errors.add(new BaseErrorDto("returnDate", ErrorMessage.EMPTY_FIELD));
    }

    if (rentToUpdate.getHaveInsurance() == null) {
      errors.add(new BaseErrorDto("haveInsurance", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  public boolean validateUUID(String idRent) {
    try {
      if (!idRent.isEmpty()) {
        UUID.fromString(idRent);
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
