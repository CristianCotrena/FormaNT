package com.example.locationCar.mock.address;

import com.example.locationCar.mock.client.ClientMockBuilder;
import com.example.locationCar.mock.employee.EmployeeMockBuilder;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import java.util.UUID;

public class AddressMockBuilder {

  public static AddressModel build() {
    ClientModel clientModel = ClientMockBuilder.build();

    EmployeeModel employeeModel = EmployeeMockBuilder.build();

    AddressModel addressModel =
        AddressModel.builder()
            .idAddress(UUID.randomUUID())
            .cep("94945-010")
            .city("Cachoeirinha")
            .complement("casa")
            .country("Brasil")
            .number(121)
            .publicPlace("Sim")
            .state("RS")
            .status(1)
            .idClient(clientModel)
            .idEmployee(employeeModel)
            .build();

    return addressModel;
  }
}
