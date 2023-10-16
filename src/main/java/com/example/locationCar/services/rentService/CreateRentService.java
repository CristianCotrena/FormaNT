package com.example.locationCar.services.rentService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateRentDto;
import com.example.locationCar.dtos.input.RentInputDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.validate.rent.CreateRentValidate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateRentService {

  private ClientRepository clientRepository;

  private EmployeeRepository employeeRepository;

  private VehicleRepository vehicleRepository;

  private RentRepository rentRepository;

  public CreateRentService(
      ClientRepository clientRepository,
      EmployeeRepository employeeRepository,
      VehicleRepository vehicleRepository,
      RentRepository rentRepository) {
    this.clientRepository = clientRepository;
    this.employeeRepository = employeeRepository;
    this.vehicleRepository = vehicleRepository;
    this.rentRepository = rentRepository;
  }

  public BaseDto inserir(RentInputDto dto) {
    List<BaseErrorDto> errors = new CreateRentValidate().validate(dto);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    Optional<ClientModel> client = clientRepository.findById(UUID.fromString(dto.getIdClient()));
    Optional<EmployeeModel> employee =
        employeeRepository.findById(UUID.fromString(dto.getIdEmployee()));
    Optional<VehicleModel> vehicle =
        vehicleRepository.findByIdVehicleAndStatus(UUID.fromString(dto.getIdVehicle()), 1);

    if (client.isEmpty()) {
      errors.add(new BaseErrorDto("idClient", ErrorMessage.CLIENT_DOESNT_EXIST));
    }

    if (employee.isEmpty()) {
      errors.add(new BaseErrorDto("idEmployee", ErrorMessage.EMPLOYEE_DOESNT_EXIST));
    }

    if (vehicle.isEmpty()) {
      errors.add(new BaseErrorDto("idVehicle", ErrorMessage.VEHICLE_DOESNT_EXIST));
    }

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    try {
      Optional<RentModel> rentExists =
          rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(
              vehicle.get(), dto.getReturnDate(), dto.getContractingDate());
      if (rentExists.isPresent()) {
        errors.add(new BaseErrorDto("idVehicle", ErrorMessage.VEHICLE_IS_UNAVAILABLE));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }
    } catch (IncorrectResultSizeDataAccessException e) {
      errors.add(new BaseErrorDto("idVehicle", ErrorMessage.VEHICLE_IS_UNAVAILABLE));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    RentModel rentModel = new RentModel();
    rentModel.setClient(client.get());
    rentModel.setEmployee(employee.get());
    rentModel.setVehicle(vehicle.get());
    rentModel.setContractingDate(dto.getContractingDate());
    rentModel.setReturnDate(dto.getReturnDate());
    rentModel.setHaveInsurance(dto.getHaveInsurance());
    rentModel.setStatus(1);

    UUID createdId = rentRepository.save(rentModel).getIdRent();

    return new ResponseSuccessBuilder<CreateRentDto>(
            HttpStatus.CREATED, new CreateRentDto(createdId.toString()), SuccessMessage.CREATE_RENT)
        .get();
  }
}
