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
import com.example.locationCar.repositories.specifications.vehicle.VehicleSpecifications;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListVehicleParamService {

  private VehicleRepository vehicleRepository;

  public BaseDto listVehicles(
      Pageable pageable, String color, Double rating, Double max, Double min) {
    List<BaseErrorDto> errors = new ArrayList<>();
    Page<VehicleModel> vehicles =
        vehicleRepository.findAll(buildSpec(color, rating, max, min), pageable);

    if (vehicles.isEmpty()) {
      errors.add(new BaseErrorDto("vehicle", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
      return result.get();
    }

    List<VehicleRecordDto> vehicleDtoList =
        vehicles.getContent().stream()
            .map(VehicleRecordDto::fromVehicleModel)
            .collect(Collectors.toList());
    Page<VehicleRecordDto> vehicleDtoPage =
        new PageImpl<>(vehicleDtoList, pageable, vehicles.getTotalElements());

    return new ResponseSuccessBuilder<Page<VehicleRecordDto>>(
            HttpStatus.OK, vehicleDtoPage, SuccessMessage.LIST_VEHICLES)
        .get();
  }

  private Specification<VehicleModel> buildSpec(
      String color, Double rating, Double max, Double min) {
    Specification<VehicleModel> spec =
        Specification.where(VehicleSpecifications.colorEquals(color))
            .and(VehicleSpecifications.ratingEquals(rating))
            .and(VehicleSpecifications.dailyValueLessThanOrEqual(max))
            .and(VehicleSpecifications.dailyValueGreaterThanOrEqual(min));
    return spec;
  }
}
