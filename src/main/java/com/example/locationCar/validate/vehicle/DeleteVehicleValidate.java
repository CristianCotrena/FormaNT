package com.example.locationCar.validate.vehicle;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeleteVehicleValidate {

  public List<BaseErrorDto> execute(UUID idVehicle, String license) {

    List<BaseErrorDto> result = new ArrayList<>();

    if (idVehicle == null && license == null) {
      BaseErrorDto error = new BaseErrorDto("[idVehicle, license]", ErrorMessage.AT_LEAST_ONE);
      result.add(error);
    }
    return result;
  }
}
