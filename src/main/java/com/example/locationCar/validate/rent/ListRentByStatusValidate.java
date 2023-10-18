package com.example.locationCar.validate.rent;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListRentByStatusValidate {

  public List<BaseErrorDto> validate(Integer status, int page) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (status == null) {
      errors.add(new BaseErrorDto("status", ErrorMessage.EMPTY_FIELD));
    }
    if (status != 0 && status != 1) {
      errors.add(new BaseErrorDto("status", ErrorMessage.INVALID_STATUS));
    }
    if (page < 0) {
      errors.add(new BaseErrorDto("page", ErrorMessage.INVALID_PAGE_NUMBER));
    }
    return errors;
  }
}