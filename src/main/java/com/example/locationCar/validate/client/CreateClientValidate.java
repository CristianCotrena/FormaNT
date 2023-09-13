package com.example.locationCar.validate.client;

import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.validate.caelumStringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateClientValidate {

  public List<BaseErrorDto> validate(ClientModel clientModel) {
    List<BaseErrorDto> errors = validateRequiredFiles(clientModel);
    return errors.size() > 0 ? errors : validateInvalidFiles(clientModel, errors);
  }

  private List<BaseErrorDto> validateRequiredFiles(ClientModel clientModel) {
    List<BaseErrorDto> errors = new ArrayList<>();
    if (clientModel.getCnh() == null || clientModel.getCnh().isEmpty()) {
      errors.add(new BaseErrorDto("cnh", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getEmail() == null || clientModel.getEmail().isEmpty()) {
      errors.add(new BaseErrorDto("email", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getCpfCnpj() == null || clientModel.getCpfCnpj().isEmpty()) {
      errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getTelephone() == null || clientModel.getTelephone().isEmpty()) {
      errors.add(new BaseErrorDto("telephone", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getName() == null || clientModel.getName().isEmpty()) {
      errors.add(new BaseErrorDto("name", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getEmergencyContact() == null || clientModel.getEmergencyContact().isEmpty()) {
      errors.add(new BaseErrorDto("emergencyContact", ErrorMessage.EMPTY_FIELD));
    }

    if (clientModel.getAge() == null) {
      errors.add(new BaseErrorDto("age", ErrorMessage.EMPTY_FIELD));
    }

    return errors;
  }

  private List<BaseErrorDto> validateInvalidFiles(
      ClientModel clientModel, List<BaseErrorDto> errors) {
    if (!Pattern.compile(RegexValues.EMAIL).matcher(clientModel.getEmail()).matches()) {
      errors.add(new BaseErrorDto("email", ErrorMessage.INVALID_FIELD));
    }

    if (!Pattern.compile(RegexValues.CNH).matcher(clientModel.getCnh()).matches()) {
      errors.add(new BaseErrorDto("cnh", ErrorMessage.INVALID_FIELD));
    }

    if (!caelumStringValidator.validarCPF(clientModel.getCpfCnpj())
        && !caelumStringValidator.validarCNPJ(clientModel.getCpfCnpj())) {
      errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.INVALID_FIELD));
    }

    if (!Pattern.compile(RegexValues.PHONE).matcher(clientModel.getTelephone()).matches()) {
      errors.add(new BaseErrorDto("telephone", ErrorMessage.INVALID_FIELD));
    }

    if (!Pattern.compile(RegexValues.PHONE).matcher(clientModel.getEmergencyContact()).matches()) {
      errors.add(new BaseErrorDto("emergencyContact", ErrorMessage.INVALID_FIELD));
    }

    if (clientModel.getAge() < 18) {
      errors.add(new BaseErrorDto("age", ErrorMessage.UNDERAGE));
    }

    return errors;
  }
}
