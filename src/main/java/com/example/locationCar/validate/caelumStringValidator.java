package com.example.locationCar.validate;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class caelumStringValidator {
  public static boolean validarCPF(String cpf) {
    CPFValidator cpfValidator = new CPFValidator();
    try {
      cpfValidator.assertValid(cpf);
      return true;
    } catch (InvalidStateException e) {
      return false;
    }
  }

  public static boolean validarCNPJ(String cnpj) {
    CNPJValidator cnpjValidator = new CNPJValidator();
    try {
      cnpjValidator.assertValid(cnpj);
      return true;
    } catch (InvalidStateException e) {
      return false;
    }
  }
}
