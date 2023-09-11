package com.example.locationCar.converters;

import com.example.locationCar.models.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

  @Override
  public String convertToDatabaseColumn(Role attribute) {
    return attribute.toString();
  }

  @Override
  public Role convertToEntityAttribute(String dbData) {
    return Role.fromString(dbData);
  }
}
