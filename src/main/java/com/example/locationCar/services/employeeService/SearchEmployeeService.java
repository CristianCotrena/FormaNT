package com.example.locationCar.services.employeeService;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SearchEmployeeService {

    EmployeeRepository employeeRepository;

    public SearchEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeModel employeeSearchById(UUID id) {
        Optional<EmployeeModel> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new IllegalArgumentException("Funcionário não encontrado pelo ID informado.");
        }
    }

    public EmployeeModel employeeSearchByCpfCnpj(String cpfCnpj) {
        EmployeeModel employeeModel = employeeRepository.findByCpfCnpj(cpfCnpj);
        if (employeeModel != null) {
            return employeeModel;
        } else {
            throw new IllegalArgumentException("Funcionário não encontrado pelo CPF/CNPJ informado.");
        }
    }

    public EmployeeModel employeeSearchByEmail(String email) {
            EmployeeModel employeeModel = employeeRepository.findByEmail(email);
            if (employeeModel != null) {
                return employeeModel;
            } else {
                throw new IllegalArgumentException("Funcionário não encontrado pelo e-mail informado.");
            }
    }
}