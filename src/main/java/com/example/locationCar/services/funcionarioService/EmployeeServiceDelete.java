package com.example.locationCar.services.funcionarioService;

import com.example.locationCar.models.EmployeeModel;
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


    public EmployeeModel deleteEmployee(UUID employeeId) {
        Optional<EmployeeModel> employeeO = employeeRepository.findById(employeeId);

        employeeRepository.deleteById(employeeO.get().getEmployeeId());
        return employeeO.orElse(null);
    }
}
