package com.example.locationCar.converters;

import com.example.locationCar.models.enums.ContractType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ContractTypeConverter implements AttributeConverter<ContractType, String> {

  @Override
  public String convertToDatabaseColumn(ContractType attribute) {
    return attribute.toString();
  }

  @Override
  public ContractType convertToEntityAttribute(String dbData) {
    return ContractType.fromString(dbData);
  }
}
