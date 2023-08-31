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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public BaseDto updateEmployee(UUID employeeId, EmployeeUpdateDto employeeUpdateDto, boolean recontratar) {

        EmployeeModel employeeToUpdate = employeeRepository.findById(employeeId)
                .orElse(null);

        if (employeeToUpdate == null) {
            BaseErrorDto error = new BaseErrorDto("ID", ErrorMessage.NOT_FOUND);
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, (List<BaseErrorDto>) error);
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

        if (!("CLT".equals(employeeUpdateDto.getContractType()) || "CNPJ".equals((employeeUpdateDto.getContractType())))) {
            errors.add((new BaseErrorDto("contractType", ErrorMessage.NEGATIVE_UPDATE)));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
            return result.get();
        }

        if (!employeeToUpdate.getContractType().equals(ContractType.fromString(employeeUpdateDto.getContractType())) ||
                !employeeToUpdate.getCpfCnpj().equals(employeeUpdateDto.getCpfCnpj()) ||
                !employeeToUpdate.getEmail().equals(employeeUpdateDto.getEmail())) {

            String fieldNames = getUpdatedFieldNames(employeeToUpdate, employeeUpdateDto);

            BaseErrorDto error = new BaseErrorDto(fieldNames, ErrorMessage.NEGATIVE_UPDATE);
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, List.of(error));
            return result.get();
        }

        employeeToUpdate.setRole(Role.fromString(employeeUpdateDto.getRole()));
        employeeToUpdate.setPosition(Position.fromString(employeeUpdateDto.getPosition()));
        employeeToUpdate.setPhone(employeeUpdateDto.getPhone());
        employeeToUpdate.setName(employeeUpdateDto.getName());

        employeeRepository.save(employeeToUpdate);

        return new ResponseSuccessBuilder<CreateEmployeeDto>(HttpStatus.OK, new CreateEmployeeDto(employeeId.toString()), SuccessMessage.UPDATE_EMPLOYEE).get();
    }

    private String getUpdatedFieldNames(EmployeeModel employeeToUpdate, EmployeeUpdateDto employeeUpdateDto) {
        List<String> updatedFields = new ArrayList<>();

        if (!employeeToUpdate.getCpfCnpj().equals(employeeUpdateDto.getCpfCnpj())) {
            updatedFields.add("cpfCnpj");
        }

        if (!employeeToUpdate.getEmail().equals(employeeUpdateDto.getEmail())) {
            updatedFields.add("email");
        }

        return String.join(", ", updatedFields);
    }
}

