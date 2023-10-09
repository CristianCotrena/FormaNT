package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.dtos.RentUpdateReturnDto;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.validate.rent.UpdateRentValidate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateRentService {
  private final RentRepository rentRepository;

  @Autowired
  public UpdateRentService(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  public BaseDto updateRent(String rentId, RentUpdateDto rentUpdateDto) {
    List<BaseErrorDto> errors = new UpdateRentValidate().validateParams(rentId, rentUpdateDto);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);

      return result.get();
    }

    if (new UpdateRentValidate().validateUUID(rentId) == false) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_UUID);

      return result.get();
    }

    RentModel rentToUpdate = rentRepository.findById(UUID.fromString(rentId)).orElse(null);

    if (rentToUpdate == null) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND);

      return result.get();
    }

    LocalDate formattedReturnDateToUpdate =
        rentUpdateDto.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate formattedReturnDateBase =
        rentToUpdate.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    if (formattedReturnDateToUpdate.compareTo(formattedReturnDateBase) <= 0) {
      List<BaseErrorDto> returnDateError = new ArrayList<>();
      returnDateError.add(
          new BaseErrorDto("returnDate", ErrorMessage.RETURN_DATE_SHORTER_THAN_CURRENT));
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, returnDateError);

      return result.get();
    }

    List<BaseErrorDto> errorsToUpdate =
        new UpdateRentValidate().validateUpdate(rentUpdateDto, rentToUpdate);

    if (errorsToUpdate.size() > 0) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errorsToUpdate);

      return result.get();
    }

    RentModel rentUpdated = rentToUpdate;
    rentUpdated.setReturnDate(rentUpdateDto.getReturnDate());
    rentRepository.save(rentUpdated);

    return new ResponseSuccessBuilder<>(
            HttpStatus.OK,
            new RentUpdateReturnDto(rentUpdated.getIdRent()),
            SuccessMessage.UPDATE_RENT)
        .get();
  }
}
