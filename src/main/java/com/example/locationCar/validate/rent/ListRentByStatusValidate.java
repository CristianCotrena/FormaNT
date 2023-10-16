package com.example.locationCar.validate.rent;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
@ComponentScan
public class ListRentByStatusValidate {

    public List<BaseErrorDto> validate(Integer status) {
        List<BaseErrorDto> errors = new ArrayList<>();

        if (status == null) {
            errors.add(new BaseErrorDto("status", ErrorMessage.EMPTY_FIELD));
        } else if (status != 0 && status != 1) {
            errors.add(new BaseErrorDto("status", ErrorMessage.INVALID_STATUS));
        }

        return errors;
    }
}