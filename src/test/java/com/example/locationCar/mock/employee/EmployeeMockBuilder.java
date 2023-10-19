package com.example.locationCar.mock.employee;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import java.util.UUID;

public class EmployeeMockBuilder {

  public static EmployeeModel build() {
    EmployeeModel employeeModel =
        EmployeeModel.builder()
            .employeeId(UUID.randomUUID())
            .name("Adrielly")
            .cpfCnpj("44190639800")
            .email("teste@teste.com")
            .phone("11447523780")
            .registry("001")
            .position(Position.ESTOQUISTA)
            .contractType(ContractType.CLT)
            .role(Role.ADMINISTRADOR)
            .status(1)
            .build();

    return employeeModel;
  }
}
