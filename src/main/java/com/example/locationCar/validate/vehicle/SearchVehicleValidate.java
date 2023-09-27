package com.example.locationCar.validate.vehicle;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class SearchVehicleValidate {
  public List<BaseErrorDto> validate(UUID idVehicle, String license) {
    List<BaseErrorDto> errors = validateRequiredFiles(idVehicle, license);
    return errors.size() > 0 ? errors : validateInvalidFiles(license, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(UUID idVehicle, String license) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if (idVehicle == null && license == null) {
      errors.add(new BaseErrorDto("id", ErrorMessage.AT_LEAST_ONE));
      errors.add(new BaseErrorDto("license", ErrorMessage.AT_LEAST_ONE));
    }
    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(String license, List<BaseErrorDto> errors) {
    if (!Pattern.compile(RegexValues.LICENSE).matcher(license).matches()) {
      errors.add(new BaseErrorDto("license", ErrorMessage.INVALID_FIELD));
    }
    return errors;
  }
}
