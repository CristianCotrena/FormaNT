package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListRentByStatusService {

    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ListRentByStatusService(RentRepository rentRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
    }

        public BaseDto<List<VehicleModel>> listRentByStatus(Integer status, int page) {
            List<BaseErrorDto> errors = validate(status);
            if (!errors.isEmpty()) {
                return new ResponseErrorBuilder<List<VehicleModel>>(HttpStatus.BAD_REQUEST, errors).get();
            }

            ZonedDateTime currentDate = ZonedDateTime.now();
            List<VehicleModel> vehicles;

            Pageable pageable = PageRequest.of(page, 10);
            List<String> vehicleIds = null;
            if (status == 0) {
                vehicles = vehicleRepository.findAvailableVehicles(null, pageable);
            } else {
                List<RentModel> allRents = rentRepository.findAllByStatus(status);
                vehicleIds = new ArrayList<>();
                for (RentModel rent : allRents) {
                    if (rent.getReturnDate().isAfter(currentDate)) {
                        vehicleIds.add(rent.getVehicle().getId().toString());
                    }
                }
                vehicles = vehicleRepository.findRentedVehicles(vehicleIds, pageable);
            }

            if (vehicles.isEmpty()) {
                errors.add(new BaseErrorDto("status", ErrorMessage.NO_VEHICLES_FOUND));
                return new ResponseErrorBuilder<List<VehicleModel>>(HttpStatus.NOT_FOUND, errors).get();
            } else {
                return new ResponseSuccessBuilder<List<VehicleModel>>(HttpStatus.OK, vehicles, SuccessMessage.LIST_RENT_BY_STATUS).get();
            }
        }

    private List<BaseErrorDto> validate(Integer status) {
        List<BaseErrorDto> errors = new ArrayList<>();
        if (status != 0 && status != 1) {
            errors.add(new BaseErrorDto("status", ErrorMessage.INVALID_STATUS));
        }
        return errors;
    }

    private List<String> convertVehicleListToStringList(List<VehicleModel> vehicles) {
        List<String> vehicleId = new ArrayList<>();
        for (VehicleModel vehicle : vehicles) {
            vehicleId.add(vehicle.getId());
        }
        return vehicleId;
    }
}
