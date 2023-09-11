package com.example.locationCar.validate.client;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class ListClientValidate {

  public List<BaseErrorDto> validateParamsToSearch(String age, String page) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (age != null) {
      try {
        Integer ageToSearch = Integer.parseInt(age);

        if (ageToSearch < 18) {
          errors.add(new BaseErrorDto("age", ErrorMessage.AGE_GREATER_THAN_OR_EQUAL_18));
        }
      } catch (Exception e) {
        errors.add(new BaseErrorDto("age", ErrorMessage.INVALID_FIELD));
      }
    }

    if (page != null) {
      try {
        Integer.parseInt(page);
      } catch (Exception e) {
        errors.add(new BaseErrorDto("page", ErrorMessage.INVALID_PAGE));
      }
    }

    return errors;
  }
}
