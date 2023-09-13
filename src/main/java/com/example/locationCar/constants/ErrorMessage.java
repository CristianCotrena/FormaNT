package com.example.locationCar.constants;

public @interface ErrorMessage {
  String EMPTY_FIELD = "Campo obrigatório";
  String INVALID_FIELD = "Campo inválido";
  String UNIQUE_FIELD = "Campo já cadastrado na base de dados";
  String UNDERAGE = "Idade deve ser maior que 18";
  String NEGATIVE_DAILY_VALUE = "O valor da diária deve ser maior que 0";
  String NOT_FOUND = "Não encontrado";
  String INVALID_PAGE = "Página informada inválida";
  String AT_LEAST_ONE = "Pelo menos um desses campos deve ser informado.";
  String NOT_FOUND_BY_PARAMS =
      "Não foi possível localizar na base de dados com os seguinte parâmetros: ";
  String NEGATIVE_UPDATE = "Não é possível alterar esse campo";
  String AGE_GREATER_THAN_OR_EQUAL_18 = "Idade deve ser maior ou igual a 18";
  String INVALID_JSON = "JSON - Formato inválido";
}
