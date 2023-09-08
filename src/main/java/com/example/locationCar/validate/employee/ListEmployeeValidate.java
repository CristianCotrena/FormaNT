package com.example.locationCar.validate.employee;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListEmployeeValidate {

  public List<BaseErrorDto> validateParamsToSearch(String role, String position, String page) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (role != null) {
      if (!isValidRole(role)) {
        errors.add(new BaseErrorDto("role", ErrorMessage.INVALID_FIELD));
      }
    }

    if (position != null) {
      if (!isValidPosition(position)) {
        errors.add(new BaseErrorDto("position", ErrorMessage.INVALID_FIELD));
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

  private boolean isValidRole(String role) {
    return Arrays.stream(Role.values()).anyMatch(r -> r.name().equals(role));
  }

  private boolean isValidPosition(String position) {
    return Arrays.stream(Position.values()).anyMatch(r -> r.name().equals(position));
  }
}
