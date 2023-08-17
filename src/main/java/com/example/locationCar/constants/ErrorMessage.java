package com.example.locationCar.constants;

public @interface ErrorMessage {
    String EMPTY_FIELD = "Campo obrigatório";
    String INVALID_FIELD = "Campo inválido";
    String UNIQUE_FIELD = "Campo já cadastrado na base de dados";
    String UNDERAGE = "Idade deve ser maior que 18";
    String NEGATIVE_DAILY_VALUE = "O valor da diária deve ser maior que 0";
}
