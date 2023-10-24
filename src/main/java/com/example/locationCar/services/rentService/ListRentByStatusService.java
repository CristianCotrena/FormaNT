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
import com.example.locationCar.validate.rent.ListRentByStatusValidate;
import java.time.ZonedDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListRentByStatusService {

  private final RentRepository rentRepository;
  private final VehicleRepository vehicleRepository;

  public BaseDto listRentByStatus(Integer status, String page) {
    List<BaseErrorDto> errors = new ListRentByStatusValidate().validate(status, page);

    if (errors.size() > 0) {
      return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors).get();
    }

    ZonedDateTime currentDate = ZonedDateTime.now();
    Optional<List<RentModel>> rentsOptional =
        rentRepository.findByReturnDateGreaterThan(currentDate);

    List<RentModel> rents = rentsOptional.orElse(Collections.emptyList());
    List<UUID> vehicleIds = new ArrayList<>();

    for (RentModel rent : rents) {
      if (rent.getVehicle() != null && rent.getVehicle().getIdVehicle() != null) {
        vehicleIds.add(rent.getVehicle().getIdVehicle());
      }
    }

    Page<VehicleModel> vehicles = Page.empty();

    int pageToSearch = 0;
    if (page != null) pageToSearch = Integer.parseInt(page);

    PageRequest pageRequest = PageRequest.of(pageToSearch, 10);

    if (status == 0) {
      if (vehicles.isEmpty()) {
        vehicles = vehicleRepository.findAll(pageRequest);
      } else {
        vehicles = vehicleRepository.findByIdVehicleNotIn(vehicleIds, pageRequest);
      }
    } else {
      vehicles = vehicleRepository.findByIdVehicleIn(vehicleIds, pageRequest);
    }

    if (vehicles == null || vehicles.isEmpty()) {
      return new ResponseErrorBuilder(
              HttpStatus.NOT_FOUND, List.of(new BaseErrorDto("vehicles", ErrorMessage.EMPTY_PAGE)))
          .get();
    }

    return new ResponseSuccessBuilder(HttpStatus.OK, vehicles, SuccessMessage.LIST_RENT_BY_STATUS)
        .get();
  }
}
