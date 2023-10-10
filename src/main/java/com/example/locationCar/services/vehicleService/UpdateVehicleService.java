package com.example.locationCar.services.vehicleService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateVehicleDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.validate.vehicle.UpdateVehicleValidate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService {

  private final VehicleRepository vehicleRepository;
  private final UpdateVehicleValidate updateVehicleValidate;

  public UpdateVehicleService(
      VehicleRepository vehicleRepository, UpdateVehicleValidate updateVehicleValidate) {
    this.vehicleRepository = vehicleRepository;
    this.updateVehicleValidate = updateVehicleValidate;
  }

  public BaseDto updateVehicle(UUID vehicleId, VehicleInputDto updateDto) {
    Optional<VehicleModel> existingVehicleOptional = vehicleRepository.findById(vehicleId);

    if (existingVehicleOptional.isEmpty()) {
      List<BaseErrorDto> notFoundErrors = List.of(new BaseErrorDto("ID", ErrorMessage.NOT_FOUND));
      return new ResponseErrorBuilder(HttpStatus.NOT_FOUND, notFoundErrors).get();
    }

    VehicleModel existingVehicle = existingVehicleOptional.get();
    List<BaseErrorDto> errors = updateVehicleValidate.validate(updateDto, existingVehicle);

    validateLicenseUniqueness(updateDto, existingVehicle, errors);

    if (!errors.isEmpty()) {
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors).get();
    }

    if (vehicleId != null) {
      String vehicleIdString = vehicleId.toString();
      existingVehicle.setColor(updateDto.getColor());
      existingVehicle.setLicense(updateDto.getLicense());
      existingVehicle.setBrand(updateDto.getBrand());
      existingVehicle.setFuel(updateDto.getFuel());
      existingVehicle.setDailyValue(updateDto.getDailyValue());
      existingVehicle.setMileage(updateDto.getMileage());
      existingVehicle.setModel(updateDto.getModel());
      existingVehicle.setRating(updateDto.getRating());

      vehicleRepository.save(existingVehicle);

      return new ResponseSuccessBuilder<CreateVehicleDto>(
              HttpStatus.OK, new CreateVehicleDto(vehicleIdString), SuccessMessage.UPDATE_VEHICLE)
          .get();
    } else {
      List<BaseErrorDto> idNullErrors = List.of(new BaseErrorDto("ID", ErrorMessage.NOT_FOUND));
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, idNullErrors).get();
    }
  }

  private void validateLicenseUniqueness(
      VehicleInputDto updatedDto, VehicleModel existingVehicle, List<BaseErrorDto> errors) {
    Optional<VehicleModel> vehicleWithSameLicense =
        vehicleRepository.findByLicense(updatedDto.getLicense());

    if (vehicleWithSameLicense.isPresent()
        && !vehicleWithSameLicense.get().getIdVehicle().equals(existingVehicle.getIdVehicle())) {
      errors.add(new BaseErrorDto("license", ErrorMessage.UNIQUE_FIELD));
    }
  }
}
