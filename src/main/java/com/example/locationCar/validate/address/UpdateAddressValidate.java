package com.example.locationCar.validate.address;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.AddressUpdateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateAddressValidate {

    public List<BaseErrorDto> validate(AddressUpdateDto addressUpdateDto) {
        List<BaseErrorDto> errors = validateRequiredFiles(addressUpdateDto);
        return !errors.isEmpty() ? errors : validateInvalidFiles(addressUpdateDto, errors);
    }

    private List<BaseErrorDto> validateRequiredFiles(AddressUpdateDto addressUpdateDto) {
        List<BaseErrorDto> errors = new ArrayList<>();

        if (addressUpdateDto.getCep() == null || addressUpdateDto.getCep().isEmpty()) {
            errors.add(new BaseErrorDto("cep", ErrorMessage.EMPTY_FIELD));
        }

        if (addressUpdateDto.getNumber() == null) {
            errors.add(new BaseErrorDto("number", ErrorMessage.EMPTY_FIELD));
        }

        if (addressUpdateDto.getComplement() == null || addressUpdateDto.getComplement().isEmpty()) {
            errors.add(new BaseErrorDto("complement", ErrorMessage.EMPTY_FIELD));
        }

        if (addressUpdateDto.getCity() != null) {
            errors.add(new BaseErrorDto("city", ErrorMessage.CEP_ERROR));
        }

        if (addressUpdateDto.getState() != null) {
            errors.add(new BaseErrorDto("state", ErrorMessage.CEP_ERROR));
        }

        if (addressUpdateDto.getCountry() != null) {
            errors.add(new BaseErrorDto("country", ErrorMessage.CEP_ERROR));
        }

        return errors;
    }

    private List<BaseErrorDto> validateInvalidFiles(
            AddressUpdateDto addressUpdateDto, List<BaseErrorDto> errors) {
        if (!Pattern.compile(RegexValues.CEP).matcher(addressUpdateDto.getCep()).matches()) {
            errors.add(new BaseErrorDto("cep", ErrorMessage.INVALID_FIELD));
        }

        return errors;
    }
}
