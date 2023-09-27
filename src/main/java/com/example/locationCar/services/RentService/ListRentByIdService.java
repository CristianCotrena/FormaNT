package com.example.locationCar.services.RentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.repositories.RentRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListRentByIdService {

    private RentRepository rentRepository;


    @Autowired
    public ListRentByIdService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;

    }

    public BaseDto listRentById(UUID clientId, UUID employeeID, Pageable pageable) {
        List<BaseErrorDto> errors = new ArrayList<>();

        if (clientId == null && employeeID == null) {
            errors.add(new BaseErrorDto("idClient", ErrorMessage.AT_LEAST_ONE));
            errors.add(new BaseErrorDto("idFuncionario", ErrorMessage.AT_LEAST_ONE));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);

            return result.get();
        }

        Page<RentModel> rents;

        if (clientId != null && employeeID != null) {
            rents = rentRepository.findByClientIdAndEmployeeId(clientId, employeeID, pageable);
        } else if (clientId != null) {
            rents = rentRepository.findByClientId(clientId, pageable);
        } else {
            rents = rentRepository.findByEmployeeId(employeeID, pageable);
        }

        if (rents.isEmpty()) {
            errors.add(new BaseErrorDto("idClient", ErrorMessage.NOT_FOUND));
            errors.add(new BaseErrorDto("idEmployee", ErrorMessage.NOT_FOUND));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
            return result.get();
        }

        return new ResponseSuccessBuilder<>(HttpStatus.OK, rents, SuccessMessage.LIST_RENT).get();

    }
}
