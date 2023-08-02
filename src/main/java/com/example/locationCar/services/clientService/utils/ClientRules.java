package com.example.locationCar.services.clientService.utils;

import com.example.locationCar.models.ClientModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRules {

    public String encryptPassword(String password) {
       String passwordEncripted = BCrypt.hashpw(password, BCrypt.gensalt(10));
       return passwordEncripted;
    }
}
