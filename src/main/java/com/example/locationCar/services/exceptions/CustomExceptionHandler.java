package com.example.locationCar.services.exceptions;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_JSON,new ArrayList<>());
        return ResponseEntity.status(result.get().getResult().getStatusCode()).body(result.get());
    }
}