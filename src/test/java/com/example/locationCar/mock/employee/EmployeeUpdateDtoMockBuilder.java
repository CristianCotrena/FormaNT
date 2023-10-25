package com.example.locationCar.mock.employee;

import com.example.locationCar.dtos.EmployeeUpdateDto;

public class EmployeeUpdateDtoMockBuilder {

  public static EmployeeUpdateDto build() {
    EmployeeUpdateDto employeeUpdateDto =
        EmployeeUpdateDto.builder()
            .name("Adrielly")
            .cpfCnpj("44190639800")
            .email("teste@teste.com")
            .phone("11447523780")
            .position("ESTOQUISTA")
            .contractType("CLT")
            .role("ADMINISTRADOR")
            .build();

    return employeeUpdateDto;
  }
}
