package com.example.locationCar.converters;

import com.example.locationCar.models.enums.Position;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PositionConverter implements AttributeConverter<Position, String> {

  @Override
  public String convertToDatabaseColumn(Position attribute) {
    return attribute.toString();
  }

  @Override
  public Position convertToEntityAttribute(String dbData) {
    return Position.fromString(dbData);
  }
}
