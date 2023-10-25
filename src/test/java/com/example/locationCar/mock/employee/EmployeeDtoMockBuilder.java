package com.example.locationCar.mock.employee;

import com.example.locationCar.dtos.EmployeeDto;

public class EmployeeDtoMockBuilder {

  public static EmployeeDto build() {
    EmployeeDto employeeDto =
        EmployeeDto.builder()
            .name("Adrielly")
            .cpfCnpj("44190639800")
            .email("teste@teste.com")
            .phone("11447523780")
            .registry("001")
            .position("ESTOQUISTA")
            .contractType("CLT")
            .role("ADMINISTRADOR")
            .build();

    return employeeDto;
  }
}
