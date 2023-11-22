package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.SearchRentResponseDto;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SearchRentService {

  private final RentRepository rentRepository;

  public SearchRentService(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  public BaseDto searchRent(String id) {
    List<BaseErrorDto> errors = new ArrayList<>();

    if (id != null) {
      try {
        UUID rentId = UUID.fromString(id);
        Optional<RentModel> rent = rentRepository.findById(rentId);

        if (rent.isEmpty()) {
          errors.add(new BaseErrorDto("rent", ErrorMessage.NOT_FOUND));
          ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
          return result.get();
        }

        RentModel rentModel = rent.get();
        SearchRentResponseDto rentSearchDto = new SearchRentResponseDto(rentModel);

        return new ResponseSuccessBuilder<>(
                HttpStatus.OK, rentSearchDto, SuccessMessage.SEARCH_RENT)
            .get();
      } catch (IllegalArgumentException e) {
        errors.add(new BaseErrorDto("rent", ErrorMessage.INVALID_FIELD));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }
    } else {
      errors.add(new BaseErrorDto("id", ErrorMessage.EMPTY_FIELD));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }
  }
}
