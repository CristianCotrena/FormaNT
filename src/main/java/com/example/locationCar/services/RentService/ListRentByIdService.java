package com.example.locationCar.services.RentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.base.dto.BaseResultDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ListRentByIdService {
    private final RentRepository rentRepository;

    @Autowired
    public ListRentByIdService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public BaseDto getRentsByClientId(UUID clientId) {
        List<RentModel> rent = rentRepository.findByClient_IdClient(clientId);
        List<BaseErrorDto> errors = new ArrayList<>();


        if (!rent.isEmpty()) {
            ResponseSuccessBuilder result = new ResponseSuccessBuilder(HttpStatus.OK, rent, SuccessMessage.LIST_RENT);
            return result.get();
        } else {
            errors.add(new BaseErrorDto("clientId", ErrorMessage.NOT_FOUND));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND, errors);
            return result.get();
        }
    }

    public BaseDto getRentsByEmployeeId(UUID employeeId) {
        List<RentModel> rent = rentRepository.findByEmployee_EmployeeId(employeeId);
        List<BaseErrorDto> errors = new ArrayList<>();

        if(!rent.isEmpty()) {
            ResponseSuccessBuilder result = new ResponseSuccessBuilder(HttpStatus.OK, rent, SuccessMessage.LIST_RENT);
            return result.get();
        } else {
            errors.add(new BaseErrorDto("employeeId", ErrorMessage.NOT_FOUND));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND, errors);
            return result.get();
        }
    }
}