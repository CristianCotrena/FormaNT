package com.example.locationCar.services.employeeService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateEmployeeDto;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.validate.employee.CreateEmployeeValidate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEmployeeService {

  private EmployeeRepository employeeRepository;

  public BaseDto createEmployee(EmployeeDto employeeDto) {

    List<BaseErrorDto> errors = new CreateEmployeeValidate().validate(employeeDto);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    if (employeeRepository.findByEmail(employeeDto.getEmail()) != null) {
      errors.add(new BaseErrorDto("email", ErrorMessage.UNIQUE_FIELD));
    }

    if (employeeRepository.findByCpfCnpj(employeeDto.getCpfCnpj()) != null) {
      errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.UNIQUE_FIELD));
    }

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    EmployeeModel employeeModel = new EmployeeModel();
    employeeModel.setRole(Role.fromString(employeeDto.getRole()));
    employeeModel.setEmail(employeeDto.getEmail());
    employeeModel.setPhone(employeeDto.getPhone());
    employeeModel.setPosition(Position.fromString(employeeDto.getPosition()));
    employeeModel.setName(employeeDto.getName());
    employeeModel.setCpfCnpj(employeeDto.getCpfCnpj());
    employeeModel.setContractType(ContractType.fromString(employeeDto.getContractType()));
    employeeModel.setRegistry(employeeDto.getRegistry());
    employeeModel.setStatus(1);

    UUID createdId = employeeRepository.save(employeeModel).getEmployeeId();

    return new ResponseSuccessBuilder<CreateEmployeeDto>(
            HttpStatus.CREATED,
            new CreateEmployeeDto(createdId.toString()),
            SuccessMessage.CREATE_EMPLOYEE)
        .get();
  }
}
