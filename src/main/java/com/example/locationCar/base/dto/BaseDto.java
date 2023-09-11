package com.example.locationCar.base.dto;

import java.util.List;

public class BaseDto<T> {

  private T data;
  private List<BaseErrorDto> errors;
  private BaseResultDto result;

  public BaseDto(T data, List<BaseErrorDto> errors, BaseResultDto result) {
    this.data = data;
    this.errors = errors;
    this.result = result;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public List<BaseErrorDto> getErrors() {
    return errors;
  }

  public void setErrors(List<BaseErrorDto> errors) {
    this.errors = errors;
  }

  public BaseResultDto getResult() {
    return result;
  }

  public void setResult(BaseResultDto result) {
    this.result = result;
  }
}
