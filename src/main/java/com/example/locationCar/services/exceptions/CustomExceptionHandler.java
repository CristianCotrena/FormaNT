package com.example.locationCar.services.exceptions;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseDto> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        ResponseErrorBuilder result =
                new ResponseErrorBuilder(
                        HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_JSON, new ArrayList<>());
        return ResponseEntity.status(result.get().getResult().getStatusCode()).body(result.get());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseDto> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        List<BaseErrorDto> errors = new ArrayList<>();
            errors.add(new BaseErrorDto("Id", ErrorMessage.INVALID_FIELD));

        ResponseErrorBuilder result =
                new ResponseErrorBuilder(
                        HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_FIELD, errors);
        return ResponseEntity.status(result.get().getResult().getStatusCode()).body(result.get());
    }
}


