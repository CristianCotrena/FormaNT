package com.example.locationCar.validate.vehicle;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.input.VehicleInputDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateVehicleValidate {

  public List<BaseErrorDto> validate(VehicleInputDto vehicleInputDto) {
    List<BaseErrorDto> errors = validateRequiredFiles(vehicleInputDto);
    return errors.size() > 0 ? errors : validateInvalidFiles(vehicleInputDto, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(VehicleInputDto vehicleInputDto) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if (vehicleInputDto.getLicense() == null || vehicleInputDto.getLicense().isEmpty()) {
      errors.add(new BaseErrorDto("license", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getBrand() == null || vehicleInputDto.getBrand().isEmpty()) {
      errors.add(new BaseErrorDto("brand", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getModel() == null || vehicleInputDto.getModel().isEmpty()) {
      errors.add(new BaseErrorDto("model", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getColor() == null || vehicleInputDto.getColor().isEmpty()) {
      errors.add(new BaseErrorDto("color", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getFuel() == null || vehicleInputDto.getFuel().isEmpty()) {
      errors.add(new BaseErrorDto("fuel", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getDailyValue() == null) {
      errors.add(new BaseErrorDto("dailyValue", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getMileage() == null) {
      errors.add(new BaseErrorDto("mileage", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getRating() == null) {
      errors.add(new BaseErrorDto("rating", ErrorMessage.EMPTY_FIELD));
    }

    if (vehicleInputDto.getDoorNumber() == null) {
      errors.add(new BaseErrorDto("doorNumber", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(
      VehicleInputDto vehicleInputDto, List<BaseErrorDto> errors) {
    if (!Pattern.compile(RegexValues.LICENSE).matcher(vehicleInputDto.getLicense()).matches()) {
      errors.add(new BaseErrorDto("license", ErrorMessage.INVALID_FIELD));
    }

    if (vehicleInputDto.getDoorNumber() != 2 & vehicleInputDto.getDoorNumber() != 4) {
      errors.add(new BaseErrorDto("doorNumber", ErrorMessage.INVALID_FIELD));
    }

    if (doubleValue(vehicleInputDto.getDailyValue()) <= 0) {
      errors.add(new BaseErrorDto("dailyValue", ErrorMessage.NEGATIVE_DAILY_VALUE));
    }

    return errors;
  }
}
