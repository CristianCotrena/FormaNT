package com.example.locationCar.mock.client;

import com.example.locationCar.models.ClientModel;
import java.util.UUID;

public class ClientMockBuilder {

  public static ClientModel build() {
    ClientModel clientModel =
        ClientModel.builder()
            .idClient(UUID.randomUUID())
            .name("Anna")
            .cpfCnpj("38709471014")
            .cnh("874658935467")
            .age(37)
            .telephone("51981220944")
            .emergencyContact("5133360636")
            .email("anna.hermes@gmail.com")
            .status(1)
            .build();

    return clientModel;
  }
}
