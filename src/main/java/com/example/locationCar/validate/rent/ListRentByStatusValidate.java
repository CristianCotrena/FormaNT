package com.example.locationCar.validate.rent;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListRentByStatusValidate {

  public List<BaseErrorDto> validate(Integer status, String page) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (status == null) {
      errors.add(new BaseErrorDto("status", ErrorMessage.EMPTY_FIELD));
    }

    if (status != 0 && status != 1) {
      errors.add(new BaseErrorDto("status", ErrorMessage.INVALID_FIELD));
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
