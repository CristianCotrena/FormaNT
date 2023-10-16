package com.example.locationCar.services.employeeService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateEmployeeDto;
import com.example.locationCar.dtos.EmployeeUpdateDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.validate.employee.UpdateEmployeeValidate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEmployeeService {

  private final EmployeeRepository employeeRepository;

  public BaseDto updateEmployee(
      UUID employeeId, EmployeeUpdateDto employeeUpdateDto, boolean recontratar) {

    List<BaseErrorDto> errorList = new ArrayList<>();

    EmployeeModel employeeToUpdate = employeeRepository.findById(employeeId).orElse(null);

    if (employeeToUpdate == null) {
      BaseErrorDto error = new BaseErrorDto("ID", ErrorMessage.NOT_FOUND);
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND, errorList);
      return result.get();
    }

    List<BaseErrorDto> errors = new UpdateEmployeeValidate().validate(employeeUpdateDto);

    employeeToUpdate.setRegistry(employeeUpdateDto.getRegistry());

    if (!errors.isEmpty()) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    if (recontratar) {
      if (employeeToUpdate.getStatus() == 0) {
        employeeToUpdate.setStatus(1);
      }
    }

    if (!("CLT".equals(employeeUpdateDto.getContractType())
        || "CNPJ".equals((employeeUpdateDto.getContractType())))) {
      errors.add((new BaseErrorDto("contractType", ErrorMessage.NEGATIVE_UPDATE)));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    if (!employeeToUpdate
        .getContractType()
        .equals(ContractType.fromString(employeeUpdateDto.getContractType()))) {

      BaseErrorDto error = new BaseErrorDto("contractType", ErrorMessage.NEGATIVE_UPDATE);
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, List.of(error));
      return result.get();
    }

    if (!employeeToUpdate.getCpfCnpj().equals(employeeUpdateDto.getCpfCnpj())) {

      BaseErrorDto error = new BaseErrorDto("cpfCnpj", ErrorMessage.NEGATIVE_UPDATE);
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, List.of(error));
      return result.get();
    }

    if (!employeeToUpdate.getEmail().equals(employeeUpdateDto.getEmail())) {

      BaseErrorDto error = new BaseErrorDto("email", ErrorMessage.NEGATIVE_UPDATE);
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, List.of(error));
      return result.get();
    }

    employeeToUpdate.setRole(Role.fromString(employeeUpdateDto.getRole()));
    employeeToUpdate.setPosition(Position.fromString(employeeUpdateDto.getPosition()));
    employeeToUpdate.setPhone(employeeUpdateDto.getPhone());
    employeeToUpdate.setName(employeeUpdateDto.getName());

    employeeRepository.save(employeeToUpdate);

    return new ResponseSuccessBuilder<CreateEmployeeDto>(
            HttpStatus.OK,
            new CreateEmployeeDto(employeeId.toString()),
            SuccessMessage.UPDATE_EMPLOYEE)
        .get();
  }
}
