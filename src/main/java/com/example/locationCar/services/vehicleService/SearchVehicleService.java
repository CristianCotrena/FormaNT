package com.example.locationCar.services.vehicleService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.VehicleRecordDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.validate.vehicle.SearchVehicleValidate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SearchVehicleService {

  private final VehicleRepository vehicleRepository;

  public SearchVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  public BaseDto searchVehicle(UUID idVehicle, String license) {

    SearchVehicleValidate validate = new SearchVehicleValidate();

    if (license != null) {
      List<BaseErrorDto> errors = validate.validate(null, license);

      if (errors.size() > 0) {
        ResponseErrorBuilder errorBuilder =
            new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return errorBuilder.get();
      }
    }

    if (idVehicle != null && license != null) {
      Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findById(idVehicle);

      if (vehicleModelOptional.isPresent()
          && vehicleModelOptional.get().getLicense().equals(license)) {
        VehicleRecordDto vehicleRecordDto = new VehicleRecordDto(vehicleModelOptional.get());
        return new ResponseSuccessBuilder<>(
                HttpStatus.OK, vehicleRecordDto, SuccessMessage.SEARCH_VEHICLE)
            .get();
      } else {
        return generateError(Arrays.asList(idVehicle.toString(), license));
      }
    } else if (idVehicle != null) {
      Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findById(idVehicle);
      if (vehicleModelOptional.isPresent()) {
        VehicleRecordDto vehicleRecordDto = new VehicleRecordDto(vehicleModelOptional.get());
        return new ResponseSuccessBuilder<>(
                HttpStatus.OK, vehicleRecordDto, SuccessMessage.SEARCH_VEHICLE)
            .get();
      } else {
        return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND).get();
      }
    } else if (license != null) {
      Optional<VehicleModel> vehicle = vehicleRepository.findByLicense(license);
      if (vehicle.isPresent()) {
        VehicleRecordDto vehicleRecordDto = new VehicleRecordDto(vehicle.get());
        return new ResponseSuccessBuilder<>(
                HttpStatus.OK, vehicleRecordDto, SuccessMessage.SEARCH_VEHICLE)
            .get();
      } else {
        return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND).get();
      }
    } else {
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.AT_LEAST_ONE).get();
    }
  }

  public BaseDto generateError(List<String> fields) {
    ResponseErrorBuilder errorBuilder =
        new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND_BY_PARAMS + fields);
    return errorBuilder.get();
  }
}
