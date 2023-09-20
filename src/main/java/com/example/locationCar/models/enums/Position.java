package com.example.locationCar.models.enums;

public enum Position {
  VENDEDOR("VENDEDOR"),
  ESTOQUISTA("ESTOQUISTA");

  private final String value;

  Position(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Position fromString(String value) {
    for (Position position : Position.values()) {
      if (position.getValue().equalsIgnoreCase(value)) {
        return position;
      }
    }
    throw new IllegalArgumentException("Invalid Position value: " + value);
  }
}
