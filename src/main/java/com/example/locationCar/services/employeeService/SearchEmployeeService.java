package com.example.locationCar.services.employeeService;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchEmployeeService {

  EmployeeRepository employeeRepository;

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
