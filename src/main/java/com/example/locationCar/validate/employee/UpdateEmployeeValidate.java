package com.example.locationCar.validate.employee;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.EmployeeDto;
import com.example.locationCar.dtos.EmployeeUpdateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateEmployeeValidate {

    public List<BaseErrorDto> validate(EmployeeUpdateDto employeeUpdateDto) {
        List<BaseErrorDto> errors = new ArrayList<>();

        if (!("VENDEDOR".equals(employeeUpdateDto.getPosition()) || "ESTOQUISTA".equals((employeeUpdateDto.getPosition())))) {
            errors.add((new BaseErrorDto("position", ErrorMessage.INVALID_FIELD)));
        }

        if (!Pattern.compile(RegexValues.PHONE).matcher(employeeUpdateDto.getPhone()).matches()) {
                errors.add(new BaseErrorDto("phone", ErrorMessage.INVALID_FIELD));
        }

        if (!("VENDEDOR".equals(employeeUpdateDto.getRole()) || "ADMINISTRADOR".equals((employeeUpdateDto.getRole())))) {
            errors.add((new BaseErrorDto("role", ErrorMessage.INVALID_FIELD)));
        }


        return errors;

        }
    }



