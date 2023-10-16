package com.example.locationCar.services.clientService.utils;

import org.springframework.stereotype.Service;

@Service
public class ClientRules {

  public static boolean isEmailValid(String email) {
    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
  }

  public static boolean isCnhValid(String cnh) {
    return cnh.matches(
        "^[0-9]{10,12}$"); // CNH com 12 dígitos. O padrão é 11. Adicionamos um digito a mais.
  }

  public static boolean isCPFValid(String cpf) {
    return cpf.matches("^\\d{11}$"); // CPF com 11 dígitos.
  }

  public static boolean isCNPJValid(String cnpj) {
    return cnpj.matches("^\\d{14}$"); // CNPJ com 14 dígitos.
  }

  public static boolean isPhoneValid(String phone) {
    return phone.matches("^\\d{10,11}$");
  }
}
