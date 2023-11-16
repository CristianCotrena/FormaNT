package com.example.locationCar.validate.login;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.input.LoginInputDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateLoginValidate {
    public List<BaseErrorDto> validate(LoginInputDto loginInputDto) {
        List<BaseErrorDto> errors = validateRequiredFiles(loginInputDto);
        return errors.size() > 0 ? errors : validateInvalidFiles(loginInputDto, errors);
    }

    private List<BaseErrorDto> validateRequiredFiles(LoginInputDto loginInputDto) {
        List<BaseErrorDto> errors = new ArrayList<>();
        if ((loginInputDto.getEmail() == null || loginInputDto.getEmail().isEmpty())
                && (loginInputDto.getPassword() == null || loginInputDto.getPassword().isEmpty())) {
            errors.add(new BaseErrorDto("email e password", ErrorMessage.EMPTY_FIELD));
        }

        if (loginInputDto.getEmail() == null || loginInputDto.getEmail().isEmpty()) {
            errors.add(new BaseErrorDto("email", ErrorMessage.EMPTY_FIELD));
        }

        if (loginInputDto.getPassword() == null || loginInputDto.getPassword().isEmpty()) {
            errors.add(new BaseErrorDto("password", ErrorMessage.EMPTY_FIELD));
        }

        if (loginInputDto.getIdClient() == null && loginInputDto.getEmployeeId() == null) {
            errors.add(new BaseErrorDto("idClient e employeeId", ErrorMessage.AT_LEAST_ONE));
        }

        if (loginInputDto.getIdClient() != null && loginInputDto.getEmployeeId() != null) {
            errors.add(new BaseErrorDto("idClient e employeeId", ErrorMessage.ONLY_ONE));
        }

        return errors;
    }

    private List<BaseErrorDto> validateInvalidFiles(
            LoginInputDto loginInputDto, List<BaseErrorDto> errors) {
        Boolean regexPassword = Pattern.compile(RegexValues.PASSWORD).matcher(loginInputDto.getPassword()).matches();
        if (!regexPassword) {
            errors.add(new BaseErrorDto("password", ErrorMessage.PASSWORD_ERROR));
        }

        Boolean regexEmail = Pattern.compile(RegexValues.EMAIL).matcher(loginInputDto.getEmail()).matches();
        if (!regexEmail) {
            errors.add(new BaseErrorDto("email", ErrorMessage.INVALID_FIELD));
        }

        return errors;
    }
}
