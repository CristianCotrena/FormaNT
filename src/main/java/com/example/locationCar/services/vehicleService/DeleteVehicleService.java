package com.example.locationCar.services.vehicleService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.DeleteVehicleDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.validate.vehicle.DeleteVehicleValidate;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeleteVehicleService {

  VehicleRepository vehicleRepository;

  public DeleteVehicleService(VehicleRepository vehicleRepository) {

    this.vehicleRepository = vehicleRepository;
  }

  @Transactional
  public BaseDto execute(UUID idVehicle, String license) {

    List<BaseErrorDto> errors = new DeleteVehicleValidate().execute(idVehicle, license);

    if (errors.size() > 0) {
      ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return errorBuilder.get();
    }

    if (idVehicle != null && license != null) {
      Optional<VehicleModel> vehicleModelOptional = vehicleRepository.findById(idVehicle);

      if (!(vehicleModelOptional.isPresent()
          && vehicleModelOptional.get().getLicense().equals(license))) {
        return generateError(Arrays.asList(idVehicle.toString(), license));
      }

    } else if (idVehicle != null) {
      if (!vehicleRepository.existsById(idVehicle)) {
        return generateError(Arrays.asList(idVehicle.toString()));
      }

      VehicleModel vehicleModel = vehicleRepository.findById(idVehicle).get();
      vehicleModel.setStatus(0);
      vehicleRepository.save(vehicleModel);

    } else if (license != null) {
      if (!vehicleRepository.existsByLicense(license).get()) {
        return generateError(Arrays.asList(license));
      }

      VehicleModel vehicleModel = vehicleRepository.findByLicense(license).get();
      vehicleModel.setStatus(0);
      vehicleRepository.save(vehicleModel);
    }

    DeleteVehicleDto deleteVehicleDto = new DeleteVehicleDto(true);
    ResponseSuccessBuilder<DeleteVehicleDto> responseSuccessBuilder =
        new ResponseSuccessBuilder<>(
            HttpStatus.ACCEPTED, deleteVehicleDto, SuccessMessage.DELETE_SUCCESS);
    return responseSuccessBuilder.get();
  }

  private BaseDto generateError(List<String> fields) {
    ResponseErrorBuilder errorBuilder =
        new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND_BY_PARAMS + fields);
    return errorBuilder.get();
  }
}
