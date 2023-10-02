package com.example.locationCar.constants;

public @interface RegexValues {
  String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  String CNH = "^[0-9]{10,12}$";
  String PHONE = "^\\d{10,11}$";
  String LICENSE = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}";
  String CEP = "\\d{8}";
}
