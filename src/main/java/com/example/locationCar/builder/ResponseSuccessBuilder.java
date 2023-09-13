package com.example.locationCar.builder;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseResultDto;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;

public class ResponseSuccessBuilder<T> {

  private BaseDto<T> result;

  public ResponseSuccessBuilder(HttpStatus status, T data, String message) {
    BaseResultDto result =
        new BaseResultDto(status.value(), message != null ? message : status.getReasonPhrase());
    this.result = new BaseDto<>(data, new ArrayList<>(), result);
  }

  public BaseDto<T> get() {
    return result;
  }
}
