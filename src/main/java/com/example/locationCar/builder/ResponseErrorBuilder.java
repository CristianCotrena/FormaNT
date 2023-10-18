package com.example.locationCar.builder;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.base.dto.BaseResultDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ResponseErrorBuilder {
  private BaseDto<Void> result;

  public ResponseErrorBuilder(HttpStatus status, String message) {
    BaseResultDto result =
        new BaseResultDto(status.value(), message != null ? message : status.getReasonPhrase());
    this.result = new BaseDto<>(null, new ArrayList<>(), result);
  }

  public ResponseErrorBuilder(HttpStatus status, String message, List<BaseErrorDto> errors) {
    BaseResultDto result =
        new BaseResultDto(status.value(), message != null ? message : status.getReasonPhrase());
    this.result = new BaseDto<>(null, errors, result);
  }

  public ResponseErrorBuilder(HttpStatus status, List<BaseErrorDto> errors) {
    BaseResultDto result = new BaseResultDto(status.value(), status.getReasonPhrase());
    this.result = new BaseDto<>(null, errors, result);
  }

  public void addError(String field, String message) {
    BaseErrorDto baseErrorDto = new BaseErrorDto(field, message);
    result.getErrors().add(baseErrorDto);
  }

  public BaseDto<Void> get() {
    return result;
  }
}
