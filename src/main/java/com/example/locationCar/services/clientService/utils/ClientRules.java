package com.example.locationCar.services.clientService.utils;

import com.example.locationCar.models.ClientModel;

public class ClientRules {
    ClientModel clientModel;

    public ClientRules(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public boolean isValidAge () {
        int age = clientModel.getAge();
        return age >= 18;
    }


}
