package com.example.locationCar.mock.rent;

import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.mock.employee.EmployeeMockBuilder;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.RentModel;
import java.time.ZonedDateTime;
import java.util.UUID;

public class RentMockBuilder {

  public static RentModel build() {
    ClientModel clientModel = ClientMockBuilder.build();

    EmployeeModel employeeModel = EmployeeMockBuilder.build();

    RentModel rentModel =
        RentModel.builder()
            .idRent(UUID.randomUUID())
            .contractingDate(ZonedDateTime.parse("2023-12-25T03:00:00Z"))
            .returnDate(ZonedDateTime.parse("2024-01-11T03:00:00Z"))
            .haveInsurance(1)
            .status(1)
            .client(clientModel)
            .employee(employeeModel)
            .build();

    return rentModel;
  }
}
