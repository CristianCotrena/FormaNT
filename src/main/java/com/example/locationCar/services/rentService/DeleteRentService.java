package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.DeleteRentDto;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteRentService {

    private RentRepository rentRepository;

    public DeleteRentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public BaseDto remove(UUID idRent) {
        List<BaseErrorDto> errors = new ArrayList<>();

        Optional<RentModel> rentModel = rentRepository.findById(idRent);

        if (rentModel.isEmpty()) {
            errors.add(new BaseErrorDto("idRent", ErrorMessage.NOT_FOUND));
        }

        if (errors.size() > 0) {
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
            return result.get();
        }

        rentModel.get().setStatus(0);

        return new ResponseSuccessBuilder<DeleteRentDto>(
                HttpStatus.OK,
                new DeleteRentDto(idRent.toString()),
                SuccessMessage.DELETE_SUCCESS)
                .get();
    }
}
