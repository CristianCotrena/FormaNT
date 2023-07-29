package com.example.locationCar.services.funcionarioService;

import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.FuncionarioRepository;

import java.util.Optional;
import java.util.UUID;

public class FuncionarioServiceDelete {

    FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceDelete(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    public FuncionarioModel deleteFuncionario(UUID funcionarioId) {
        Optional<FuncionarioModel> funcionarioO = funcionarioRepository.findById(funcionarioId);

        funcionarioRepository.deleteById(funcionarioO.get().getIdFuncionario());
        return funcionarioO.orElse(null);
    }
}
