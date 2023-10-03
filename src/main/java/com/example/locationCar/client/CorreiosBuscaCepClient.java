package com.example.locationCar.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CorreiosBuscaCepClient {

  public static ResponseViaCep getAddressInformation(String cep) {
    RestTemplate restTemplate = new RestTemplate();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("https://viacep.com.br/ws/");
    stringBuilder.append(cep);
    stringBuilder.append("/json/");
    ResponseEntity<ResponseViaCep> response =
        restTemplate.getForEntity(stringBuilder.toString(), ResponseViaCep.class);
    return response.getBody();
  }
}
