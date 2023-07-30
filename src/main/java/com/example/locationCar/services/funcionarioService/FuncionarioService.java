package com.example.locationCar.services.funcionarioService;

import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.models.enums.Cargo;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.TipoContrato;
import com.example.locationCar.repositories.FuncionarioRepository;
import jakarta.validation.constraints.Size;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FuncionarioService {
    private static FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public static FuncionarioModel saveFuncionario(FuncionarioModel funcionario) {

        if (!isValidCargo(funcionario.getCargo())) {
            throw new IllegalArgumentException("Cargo inválido.");
        }

        if (!isValidTipoContrato(funcionario.getTipoContrato())) {
            throw new IllegalArgumentException("Tipo de contrato inválido.");
        }

        if (!isValidRole(funcionario.getRole())) {
            throw new IllegalArgumentException("Role inválida.");
        }

        if (!isValidEmail(funcionario.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (!isValidTelefone(funcionario.getTelefone())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }


        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            throw new IllegalArgumentException("Já existe um funcionario cadastrado com este e-mail");
        }



        return funcionarioRepository.save(funcionario);
    }

    private static boolean isValidCargo(Cargo cargo) {
        return cargo != null && (cargo == Cargo.VENDEDOR || cargo == Cargo.ESTOQUISTA);
    }

    private static boolean isValidRole(Role role) {
        return role != null && (role == Role.VENDEDOR || role == Role.ADMINISTRADOR);
    }

    private static boolean isValidTipoContrato(TipoContrato tipoContrato) {
        return tipoContrato != null && (tipoContrato == TipoContrato.CLT || tipoContrato == TipoContrato.CNPJ);
    }

    private static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    private static boolean isValidTelefone(String telefone) {
        String telefoneFormato = "\\(\\d{2}\\) \\d{4}-\\d{4}"; // formato (XX) XXXX-XXXX
        return Pattern.matches(telefoneFormato, telefone);
    }


}


