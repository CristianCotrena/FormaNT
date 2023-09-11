package com.example.locationCar.models.enums;

public enum Role {
  VENDEDOR("VENDEDOR"),
  ADMINISTRADOR("ADMINISTRADOR");

  private final String value;

  Role(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Role fromString(String value) {
    for (Role role : Role.values()) {
      if (role.getValue().equalsIgnoreCase(value)) {
        return role;
      }
    }
    throw new IllegalArgumentException("Invalid Role value: " + value);
  }
}
