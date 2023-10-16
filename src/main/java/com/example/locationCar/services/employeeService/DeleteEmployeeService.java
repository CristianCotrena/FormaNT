package com.example.locationCar.services.employeeService;

import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.EmployeeRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployeeService {

  EmployeeRepository employeeRepository;

  public EmployeeModel deleteEmployee(UUID employeeId) {
    Optional<EmployeeModel> employee = employeeRepository.findById(employeeId);
    if (employee.isEmpty() || employee.get().getStatus() == 0) {
      return null;
    }

    employee.get().setStatus(0);
    employeeRepository.save(employee.get());
    return employee.get();
  }
}
