package com.example.locationCar.validate.vehicle;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UpdateVehicleValidate {

  private final VehicleRepository vehicleRepository;

  public UpdateVehicleValidate(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  public List<BaseErrorDto> validate(VehicleInputDto updatedDto, VehicleModel existingVehicle) {
    List<BaseErrorDto> errors = validateRequiredFields(updatedDto);
    validateDoorNumber(updatedDto, errors);
    validateLicense(updatedDto.getLicense(), errors);
    return errors;
  }

  private List<BaseErrorDto> validateRequiredFields(VehicleInputDto updatedDto) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (updatedDto.getBrand() == null || updatedDto.getBrand().isEmpty()) {
      errors.add(new BaseErrorDto("brand", ErrorMessage.EMPTY_FIELD));
    }

    if (updatedDto.getModel() == null || updatedDto.getModel().isEmpty()) {
      errors.add(new BaseErrorDto("model", ErrorMessage.EMPTY_FIELD));
    }

    if (updatedDto.getColor() == null
        || !(updatedDto.getColor() instanceof String)
        || StringUtils.isBlank((String) updatedDto.getColor())) {
      errors.add(new BaseErrorDto("color", ErrorMessage.EMPTY_FIELD));
    } else if ((!StringUtils.isAlpha((String) updatedDto.getColor()))) {
      errors.add(new BaseErrorDto("color", ErrorMessage.INVALID_FIELD));
    }

    if (updatedDto.getFuel() == null
        || !(updatedDto.getFuel() instanceof String)
        || StringUtils.isBlank((String) updatedDto.getFuel())) {
      errors.add(new BaseErrorDto("fuel", ErrorMessage.INVALID_FIELD));
    } else if (!StringUtils.isAlpha((String) updatedDto.getFuel())) {
      errors.add(new BaseErrorDto("fuel", ErrorMessage.INVALID_FIELD));
    }

    if (updatedDto.getDailyValue() == null) {
      errors.add(new BaseErrorDto("dailyValue", ErrorMessage.EMPTY_FIELD));
    }

    if (updatedDto.getMileage() == null) {
      errors.add(new BaseErrorDto("mileage", ErrorMessage.EMPTY_FIELD));
    }

    if (updatedDto.getRating() == null) {
      errors.add(new BaseErrorDto("rating", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  private void validateDoorNumber(VehicleInputDto updatedDto, List<BaseErrorDto> errors) {
    if (updatedDto.getDoorNumber() != null
        && updatedDto.getDoorNumber() != 2
        && updatedDto.getDoorNumber() != 4) {
      errors.add(new BaseErrorDto("doorNumber", ErrorMessage.INVALID_FIELD));
    }
  }

  private void validateLicense(String license, List<BaseErrorDto> errors) {
    if (license != null && !Pattern.compile(RegexValues.LICENSE).matcher(license).matches()) {
      errors.add(new BaseErrorDto("license", ErrorMessage.INVALID_FIELD));
    }
  }
}
