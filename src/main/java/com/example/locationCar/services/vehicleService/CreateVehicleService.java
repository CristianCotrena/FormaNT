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
import com.example.locationCar.validate.vehicle.CreateVehicleValidate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateVehicleService {

  private VehicleRepository vehicleRepository;

  @Transactional
  public BaseDto inserir(VehicleInputDto dto) {
    List<BaseErrorDto> errors = new CreateVehicleValidate().validate(dto);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    if (!vehicleRepository.findByLicense(dto.getLicense()).isEmpty()) {
      errors.add(new BaseErrorDto("license", ErrorMessage.UNIQUE_FIELD));
    }

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    VehicleModel vehicle = new VehicleModel();
    vehicle.setBrand(dto.getBrand());
    vehicle.setColor(dto.getColor());
    vehicle.setFuel(dto.getFuel());
    vehicle.setModel(dto.getModel());
    vehicle.setLicense(dto.getLicense());
    vehicle.setDoorNumber(dto.getDoorNumber());
    vehicle.setMileage(dto.getMileage());
    vehicle.setDailyValue(dto.getDailyValue());
    vehicle.setRating(dto.getRating());
    vehicle.setStatus(1);

    UUID createdId = vehicleRepository.save(vehicle).getIdVehicle();

    return new ResponseSuccessBuilder<CreateVehicleDto>(
            HttpStatus.CREATED,
            new CreateVehicleDto(createdId.toString()),
            SuccessMessage.CREATE_VEHICLE)
        .get();
  }
}
