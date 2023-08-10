package com.example.locationCar.services.employeeService;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.repositories.EmployeeRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class CreateEmployeeService {

    public static EmployeeRepository employeeRepository;
    private EmployeeRepository employeeRepository1;

    public CreateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public static  EmployeeModel saveEmployee(EmployeeModel employee) {

        ContractType contractType = employee.getContractType();
        String cpfCnpj = employee.getCpfCnpj();

        if (!isValidTipoContrato(contractType)) {
            throw new IllegalArgumentException("Tipo de contrato inválido.");
        }

        if (contractType  == ContractType.CLT) {
            if (!isValidCpf(cpfCnpj)) {
                throw new IllegalArgumentException("CPF inválido");
            }
        } else if (contractType  == ContractType.CNPJ) {
            if (!isValidCnpj(cpfCnpj)) {
                throw new IllegalArgumentException("CNPJ inválido");
            }
        } else {
            throw new IllegalArgumentException("ERRO AO VERIFICAR TIPO DE CONTRATO E CPF/CNPJ");
        }
        if (!isValidCargo(employee.getPosition())) {
            throw new IllegalArgumentException("Cargo inválido.");
        }
        if (!isValidTipoContrato(employee.getContractType())) {
            throw new IllegalArgumentException("Tipo de contrato inválido.");
        }
        if (!isValidRole(employee.getRole())) {
            throw new IllegalArgumentException("Role inválida.");
        }
        if (!isValidEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (!isValidTelefone(employee.getPhone())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        if (!isValidCpfCnpj(employee.getCpfCnpj())) {
            throw new IllegalArgumentException("CPF ou CNPJ inválido.");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Já existe um employee cadastrado com este e-mail");
        }
        if (employeeRepository.existsByCpfCnpj(employee.getCpfCnpj())) {
            throw new IllegalArgumentException("Já existe um employee cadastrado com CPF ou CNPJ");
        }
        return employeeRepository.save(employee);
    }



    public static boolean isValidCargo(Position cargo) {
        return cargo != null && (cargo == Position.VENDEDOR || cargo == Position.ESTOQUISTA);
    }

    public static boolean isValidRole(Role role) {
        return role != null && (role == Role.VENDEDOR || role == Role.ADMINISTRADOR);
    }

    public static boolean isValidTipoContrato(ContractType tipoContrato) {
        return tipoContrato != null && (tipoContrato == ContractType.CLT || tipoContrato == ContractType.CNPJ);
    }

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static boolean isValidTelefone(String telefone) {
        String telefoneFormato = "\\(\\d{2}\\) \\d{4}-\\d{4}"; // formato (XX) XXXX-XXXX
        return Pattern.matches(telefoneFormato, telefone);
    }

    public static boolean isValidCpfCnpj(String cpfCnpj) {
        String onlyNumbers = cpfCnpj.replaceAll("[^\\d]", "");

        if (onlyNumbers.length() == 11) {
            String cpf = onlyNumbers;
            return isValidCpf(onlyNumbers);
        } else if (onlyNumbers.length() == 14) {
            String cnpj = onlyNumbers;
            return isValidCnpj(onlyNumbers);
        } else {
            return false;
        }
    }

    private static boolean isValidCpf(String cpf) {

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (10 - i);
        }
        int firstVerifierDigit = 11 - (sum % 11);
        if (firstVerifierDigit >= 10) {
            firstVerifierDigit = 0;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (11 - i);
        }
        int secondVerifierDigit = 11 - (sum % 11);
        if (secondVerifierDigit >= 10) {
            secondVerifierDigit = 0;
        }

        int firstCpfDigit = cpf.charAt(9) - '0';
        int secondCpfDigit = cpf.charAt(10) - '0';
        return firstCpfDigit == firstVerifierDigit && secondCpfDigit == secondVerifierDigit;
    }

    private static boolean isValidCnpj(String cnpj) {

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int sum = 0;
        int factor = 5;
        for (int i = 0; i < 12; i++) {
            int digit = cnpj.charAt(i) - '0';
            sum += digit * factor;
            factor = (factor == 2) ? 9 : factor - 1;
        }

        int firstVerifierDigit = sum % 11;
        if (firstVerifierDigit < 2) {
            firstVerifierDigit = 0;
        } else {
            firstVerifierDigit = 11 - firstVerifierDigit;
        }

        if (cnpj.charAt(12) - '0' != firstVerifierDigit) {
            return false;
        }

        sum = 0;
        factor = 6;
        for (int i = 0; i < 13; i++) {
            int digit = cnpj.charAt(i) - '0';
            sum += digit * factor;
            factor = (factor == 2) ? 9 : factor - 1;
        }

        int secondVerifierDigit = sum % 11;
        if (secondVerifierDigit < 2) {
            secondVerifierDigit = 0;
        } else {
            secondVerifierDigit = 11 - secondVerifierDigit;
        }

        return cnpj.charAt(13) - '0' == secondVerifierDigit;
    }
}
    







