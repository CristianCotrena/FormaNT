package com.example.locationCar.models.enums;

import com.example.locationCar.constants.ErrorMessage;

public enum ContractType {
  CLT("CLT"),
  CNPJ("CNPJ");

  private final String value;

  ContractType(String value) {
    this.value = value;
  }

  public static ContractType fromString(String value) {
    for (ContractType type : ContractType.values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException(ErrorMessage.INVALID_FIELD);
  }
}
