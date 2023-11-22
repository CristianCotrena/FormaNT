package com.example.locationCar.services.RentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ListRentByIdService {
  private final RentRepository rentRepository;

  @Autowired
  public ListRentByIdService(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  public Page<BaseDto> getRentsByClientId(UUID clientId, Pageable pageable) {
    Page<RentModel> rentPage = rentRepository.findByClient_IdClient(clientId, pageable);
    List<BaseErrorDto> errors = new ArrayList<>();

    if (!rentPage.isEmpty()) {
      List<BaseDto> baseDtoList =
          rentPage.getContent().stream()
              .map(rentModel -> new BaseDto<>(rentModel, null, null))
              .collect(Collectors.toList());

      return new PageImpl<>(baseDtoList, pageable, rentPage.getTotalElements());
    } else {
      errors.add(new BaseErrorDto("clientId", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND, errors);

      return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }
  }

  public Page<BaseDto<RentModel>> getRentsByEmployeeId(UUID employeeId, Pageable pageable) {
    Page<RentModel> rentPage = rentRepository.findByEmployee_EmployeeId(employeeId, pageable);
    List<BaseErrorDto> errors = new ArrayList<>();

    if (!rentPage.isEmpty()) {
      List<BaseDto<RentModel>> baseDtoList =
          rentPage.getContent().stream()
              .map(rentModel -> new BaseDto<>(rentModel, null, null))
              .collect(Collectors.toList());

      return new PageImpl<>(baseDtoList, pageable, rentPage.getTotalElements());
    } else {
      errors.add(new BaseErrorDto("employeeId", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND, errors);

      return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }
  }
}
