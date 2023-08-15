package com.example.locationCar.constants;

public @interface RegexValues {
    String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    String CNH = "^[0-9]{10,12}$";
    String CPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    String CPF_LENGTH = "^\\d{11}$";
    String CNPJ = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$";
    String CNPJ_LENGTH = "^\\d{14}$";
    String PHONE = "^\\d{2}\\d{4,5}-\\d{4}$";
    String PHONE_LENGTH = "^\\d{10,11}$";
}
