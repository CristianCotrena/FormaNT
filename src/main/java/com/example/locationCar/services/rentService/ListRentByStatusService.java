package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ListRentByStatusService {

  private final RentRepository rentRepository;
  private final VehicleRepository vehicleRepository;

  @Autowired
  public ListRentByStatusService(
      RentRepository rentRepository, VehicleRepository vehicleRepository) {
    this.rentRepository = rentRepository;
    this.vehicleRepository = vehicleRepository;
  }

  public BaseDto listRentByStatus(Integer status, int page) {
    List<BaseErrorDto> errors = validate(status);
    if (!errors.isEmpty()) {
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors).get();
    }

    ZonedDateTime currentDate = ZonedDateTime.now();
    Optional<List<RentModel>> rents = rentRepository.findByReturnDateGreaterThan(currentDate);
    List<UUID> vehicleIds = new ArrayList<>();

    for (RentModel rent : rents.get()) {
      vehicleIds.add(rent.getVehicle().getIdVehicle());
    }

    Page<VehicleModel> vehicles = Page.empty();
    PageRequest pageRequest = PageRequest.of(page, 10);

    if (status == 0) {
      if (vehicles.isEmpty()) {
        vehicles = vehicleRepository.findAll(pageRequest);
      } else {
        vehicles = vehicleRepository.findByIdVehicleNotIn(vehicleIds, pageRequest);
      }
    } else {
      vehicles = vehicleRepository.findByIdVehicleIn(vehicleIds, pageRequest);
    }

    return new ResponseSuccessBuilder(HttpStatus.OK, vehicles, SuccessMessage.LIST_RENT_BY_STATUS)
        .get();
  }

  private List<BaseErrorDto> validate(Integer status) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if (status != 0 && status != 1) {
      errors.add(new BaseErrorDto("status", ErrorMessage.INVALID_STATUS));
    }
    return errors;
  }
}
