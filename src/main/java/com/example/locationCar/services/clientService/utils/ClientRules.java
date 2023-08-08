package com.example.locationCar.services.clientService.utils;

import com.example.locationCar.models.ClientModel;

public class ClientRules {
    ClientModel clientModel;

    public ClientRules(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public static boolean isValidAge (int age) {
        return age >= 18;
    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isCnhValid(String cnh) {
        return cnh.matches("^[0-9]{10,12}$"); // CNH com 12 dígitos. O padrão é 11. Adicionamos um digito a mais.
    }

    public static boolean isCPFValid(String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$") || cpf.matches("^\\d{11}$"); // CPF com 11 dígitos.
    }

    public static boolean isCNPJValid(String cnpj) {
        return cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$") || cnpj.matches("^\\d{14}$"); // CNPJ com 14 dígitos.
    }

    public static boolean isPhoneValid(String phone) {
        return phone.matches("^\\d{2}\\d{4,5}-\\d{4}$") || phone.matches("^\\d{10,11}$");
    }

}
