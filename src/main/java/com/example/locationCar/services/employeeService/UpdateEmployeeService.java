package com.example.locationCar.services.employeeService;

import com.example.locationCar.dtos.EmployeeRecordDto;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UpdateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public UUID updateEmployee(UUID id, EmployeeRecordDto employeeRecordDto) {
        EmployeeModel existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não existe"));

        isValidTelefone(employeeRecordDto.phone());
        existingEmployee.setName(employeeRecordDto.name());
        existingEmployee.setPosition(employeeRecordDto.position());
        existingEmployee.setRegistry(employeeRecordDto.registry());
        existingEmployee.setRole(employeeRecordDto.role());
        existingEmployee.setStatus(employeeRecordDto.status());

        if (!existingEmployee.getEmail().equals(employeeRecordDto.email())) {
            throw new IllegalArgumentException("Não é permitido alterar o e-mail do funcionário.");
        }
        if (!existingEmployee.getCpfCnpj().equals(employeeRecordDto.cpfCnpj())) {
            throw new IllegalArgumentException("Não é permitido alterar o CPF ou CNPJ do funcionário.");
        }
        if (!existingEmployee.getContractType().equals(employeeRecordDto.contractType())) {
            throw new IllegalArgumentException("Não é permitido alterar o tipo do contrato.");
        }


        employeeRepository.save(existingEmployee);


        return existingEmployee.getEmployeeId();


    }


    public static boolean validatePosition(Position position) {
        if (position == null || (position != Position.VENDEDOR && position != Position.ESTOQUISTA)) {
            throw new IllegalArgumentException("Cargo invalido");
        }
        return true;
    }

    public static boolean isValidTelefone(String telefone) {
        String telefoneFormato = "\\(\\d{2}\\) \\d{4}-\\d{4}"; // formato (XX) XXXX-XXXX
        return Pattern.matches(telefoneFormato, telefone);
    }
}

