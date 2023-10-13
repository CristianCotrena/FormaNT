package com.example.locationCar.validate.employee;

import br.com.caelum.stella.validation.*;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.validate.caelumStringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateEmployeeValidate {

  public List<BaseErrorDto> validate(EmployeeDto employeeDto) {
    List<BaseErrorDto> errors = validateRequiredFiles(employeeDto);
    return errors.size() > 0 ? errors : validateInvalidFiles(employeeDto, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(EmployeeDto employeeDto) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if (employeeDto.getRole() == null) {
      errors.add(new BaseErrorDto("role", ErrorMessage.EMPTY_FIELD));
    }

    if (employeeDto.getEmail() == null || employeeDto.getEmail().isEmpty()) {
      errors.add(new BaseErrorDto("email", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(
      EmployeeDto employeeDto, List<BaseErrorDto> errors) {
    if (!Pattern.compile(RegexValues.EMAIL).matcher(employeeDto.getEmail()).matches()) {
      errors.add(new BaseErrorDto("email", ErrorMessage.INVALID_FIELD));
    }

    if (!Pattern.compile(RegexValues.PHONE).matcher(employeeDto.getPhone()).matches()) {
      errors.add(new BaseErrorDto("phone", ErrorMessage.INVALID_FIELD));
    }

    if (!"VENDEDOR".equals(employeeDto.getPosition())
        && !"ESTOQUISTA".equals(employeeDto.getPosition())) {
      errors.add(new BaseErrorDto("position", ErrorMessage.INVALID_FIELD));
    }

    if (!"VENDEDOR".equals(employeeDto.getRole())
        && !"ADMINISTRADOR".equals(employeeDto.getRole())) {
      errors.add(new BaseErrorDto("role", ErrorMessage.INVALID_FIELD));
    }

    if (employeeDto.getContractType() == null
        || (!"CLT".equals(employeeDto.getContractType())
            && !"CNPJ".equals(employeeDto.getContractType()))) {
      errors.add(new BaseErrorDto("contractType", ErrorMessage.INVALID_FIELD));
    }

    if ("CLT".equals(employeeDto.getContractType())) {
      if (!caelumStringValidator.validarCPF(employeeDto.getCpfCnpj())) {
        errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.INVALID_FIELD));
      }
    } else if ("CNPJ".equals(employeeDto.getContractType())) {
      if (!caelumStringValidator.validarCNPJ(employeeDto.getCpfCnpj())) {
        errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.INVALID_FIELD));
      }
    }

    return errors;
  }
}
