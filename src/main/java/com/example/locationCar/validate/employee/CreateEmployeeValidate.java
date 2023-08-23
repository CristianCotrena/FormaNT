package com.example.locationCar.validate.employee;

import br.com.caelum.stella.validation.*;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.RegexValues;
import com.example.locationCar.dtos.EmployeeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class CreateEmployeeValidate {


    public List<BaseErrorDto> validate(EmployeeDto employeeDto) {
        List<BaseErrorDto> errors = validateRequiredFiles(employeeDto);
        return errors.size() > 0 ? errors : validateInvalidFiles(employeeDto, errors);
    }

    private List<BaseErrorDto> validateRequiredFiles(EmployeeDto employeeDto) {
        List<BaseErrorDto> errors = new ArrayList<>();
        if (employeeDto.getRole() == null) {
            errors.add(new BaseErrorDto("role", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getEmail() == null || employeeDto.getEmail().isEmpty()) {
            errors.add(new BaseErrorDto("email", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getCpfCnpj() == null || employeeDto.getCpfCnpj().isEmpty()) {
            errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getPhone() == null || employeeDto.getPhone().isEmpty()) {
            errors.add(new BaseErrorDto("phone", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getName() == null || employeeDto.getName().isEmpty()) {
            errors.add(new BaseErrorDto("name", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getContractType() == null) {
            errors.add(new BaseErrorDto("contractType", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getPosition() == null) {
            errors.add(new BaseErrorDto("position", ErrorMessage.EMPTY_FIELD));
        }

        if (employeeDto.getRegistry() == null || employeeDto.getRegistry().isEmpty()) {
            errors.add((new BaseErrorDto("registry", ErrorMessage.EMPTY_FIELD)));
        }

        return errors;
    }

    private List<BaseErrorDto> validateInvalidFiles(EmployeeDto employeeDto, List<BaseErrorDto> errors) {
        if (!Pattern.compile(RegexValues.EMAIL).matcher(employeeDto.getEmail()).matches()) {
            errors.add(new BaseErrorDto("email", ErrorMessage.INVALID_FIELD));
        }

        boolean isValidCpfCnpj = Pattern.compile(RegexValues.CPF).matcher(employeeDto.getCpfCnpj()).matches() ||
                Pattern.compile(RegexValues.CPF_LENGTH).matcher(employeeDto.getCpfCnpj()).matches() ||
                Pattern.compile(RegexValues.CNPJ).matcher(employeeDto.getCpfCnpj()).matches() ||
                Pattern.compile(RegexValues.CNPJ_LENGTH).matcher(employeeDto.getCpfCnpj()).matches();

        if (!Pattern.compile(RegexValues.PHONE).matcher(employeeDto.getPhone()).matches() &&
                !Pattern.compile(RegexValues.PHONE_LENGTH).matcher(employeeDto.getPhone()).matches()) {
            errors.add(new BaseErrorDto("phone", ErrorMessage.INVALID_FIELD));
        }


        if (!"VENDEDOR".equals(employeeDto.getPosition()) && !"ESTOQUISTA".equals(employeeDto.getPosition())) {
            errors.add(new BaseErrorDto("position", ErrorMessage.INVALID_FIELD));
        }

        if (!"VENDEDOR".equals(employeeDto.getRole()) && !"ADMINISTRADOR".equals(employeeDto.getRole())) {
            errors.add(new BaseErrorDto("role", ErrorMessage.INVALID_FIELD));
        }

        if (employeeDto.getContractType() == null || (!"CLT".equals(employeeDto.getContractType()) && !"CNPJ".equals(employeeDto.getContractType()))) {
            errors.add(new BaseErrorDto("contractType", ErrorMessage.INVALID_FIELD));
        }

        if ("CLT".equals(employeeDto.getContractType())) {
            if (!validarCPF(employeeDto.getCpfCnpj())) {
                errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.INVALID_FIELD));
            }
        } else if ("CNPJ".equals(employeeDto.getContractType())) {
            if (!validarCNPJ(employeeDto.getCpfCnpj())) {
                errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.INVALID_FIELD));
            }
        }

        return errors;
    }

    private boolean validarCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
    private boolean validarCNPJ(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        try {
            cnpjValidator.assertValid(cnpj);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }

}