package com.example.locationCar.services.vehicleService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListVehicleService {
  private final VehicleRepository vehicleRepository;

  public BaseDto listVehicles(String page) {
    int pageToSearch = 0;

    if (page != null) {
      try {
        pageToSearch = Integer.parseInt(page);
      } catch (Exception e) {
        ResponseErrorBuilder result =
            new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_PAGE);
        return result.get();
      }
    }

    PageRequest pageRequest = PageRequest.of(pageToSearch, 20);
    Page<VehicleModel> vehicles = vehicleRepository.findAll(pageRequest);

    if (vehicles.isEmpty()) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND);
      return result.get();
    }

    if ((vehicles.getTotalPages() - 1) < pageToSearch) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_PAGE);
      return result.get();
    }

    return new ResponseSuccessBuilder<>(HttpStatus.OK, vehicles, SuccessMessage.LIST_VEHICLES)
        .get();
  }
}
