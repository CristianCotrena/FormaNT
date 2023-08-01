package com.example.locationCar.services.funcionarioService;

import com.example.locationCar.models.FuncionarioModel;
import com.example.locationCar.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceDelete {

    EmployeeRepository employeeRepository;

    public EmployeeServiceDelete(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public FuncionarioModel deleteFuncionario(UUID funcionarioId) {
        Optional<FuncionarioModel> funcionarioO = employeeRepository.findById(funcionarioId);

        employeeRepository.deleteById(funcionarioO.get().getIdFuncionario());
        return funcionarioO.orElse(null);
    }
}
