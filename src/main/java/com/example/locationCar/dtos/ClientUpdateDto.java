package com.example.locationCar.dtos;

public record ClientUpdateDto(
    String name,
    String cpfCnpj,
    String cnh,
    Integer age,
    String telephone,
    String emergencyContact,
    String email) {}
