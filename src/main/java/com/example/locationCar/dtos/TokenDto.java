package com.example.locationCar.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class TokenDto {

    private String token;
    private Date expiresIn;

}




