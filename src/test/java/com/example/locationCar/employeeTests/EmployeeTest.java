package com.example.locationCar.employeeTests;

import com.example.locationCar.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmployeeTest {

    EmployeeRepository employeeRepository;


    public EmployeeTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Test
    void DeleteFuncionario() {
        // a partir de um funcionario já criado, recuperar seu id e deletá-lo por meio dele.

         UUID funcionarioId = UUID.fromString("aqui vai o id do funcionario");

        employeeRepository.deleteById(funcionarioId);

            // verificar se o funcionario foi deletado

        assertFalse(employeeRepository.existsById(funcionarioId));
    }
}
