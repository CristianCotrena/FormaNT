package com.example.locationCar.employeeService.Test;

import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.employeeService.CreateEmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateEmployeeTest {

    private CreateEmployeeService employeeService;


    @Test
    public void deveriaValidarCpfCnpj() {

        String cpfCnpjNaoValido = "15151";
        String cpfValido = "02516072082";
        String cnpjValido = "48740351000165";

        assertEquals(false, employeeService.isValidCpfCnpj(cpfCnpjNaoValido));
        assertEquals(true, employeeService.isValidCpfCnpj(cpfValido));
        assertEquals(true, employeeService.isValidCpfCnpj(cnpjValido));
    }

    @Test
    public void deveriaValidarTelefone() {
        String telefoneValido = "(51) 4825-9918";
        String telefoneInvalido = "(51) 378 2578";
        assertEquals(true, employeeService.isValidTelefone(telefoneValido));
        assertEquals(false, employeeService.isValidTelefone(telefoneInvalido));
    }

    @Test
    public void deveriaValidarEmail() {
        String emailValido = "teste@teste.com";
        String emailNaoValido = "teste@teste";
        assertEquals(true, employeeService.isValidEmail(emailValido));
        assertEquals(false, employeeService.isValidEmail(emailNaoValido));

    }

    @Test
    public void deveriaValidarContrato() {
        // Criar um objeto CreateEmployeeService para testar
        CreateEmployeeService createEmployeeService = new CreateEmployeeService((EmployeeRepository) employeeService);

        // Testar contrato CLT
        boolean isValidCLT = createEmployeeService.isValidTipoContrato(ContractType.CLT);
        assertTrue(isValidCLT);

        // Testar contrato CNPJ
        boolean isValidCNPJ = createEmployeeService.isValidTipoContrato(ContractType.CNPJ);
        assertTrue(isValidCNPJ);

        // Testar contrato inválido (deve retornar falso)
        boolean isValidInvalid = createEmployeeService.isValidTipoContrato(null);
        assertFalse(isValidInvalid);


    }

    @Test
    public void deveriaValidarRole() {
        // Criar um objeto CreateEmployeeService para testar
        CreateEmployeeService createEmployeeService = new CreateEmployeeService((EmployeeRepository) employeeService);

        // Testar role VENDEDOR (deve ser válido)
        boolean isValidRoleVendedor = createEmployeeService.isValidRole(Role.VENDEDOR);
        assertTrue(isValidRoleVendedor);

        // Testar role ADMINISTRADOR (deve ser válido)
        boolean isValidRoleAdministrador = createEmployeeService.isValidRole(Role.ADMINISTRADOR);
        assertTrue(isValidRoleAdministrador);

        // Testar role inválido (deve ser inválido)
        boolean isValidInvalid = createEmployeeService.isValidRole(null);
        assertFalse(isValidInvalid);
    }


    @Test
    public void deveriaValidarCargo() {

        CreateEmployeeService createEmployeeService = new CreateEmployeeService((EmployeeRepository) employeeService);

        // Testar cargo VENDEDOR (deve ser válido)
        boolean isValidCargoVendedor = createEmployeeService.isValidCargo(Position.VENDEDOR);
        assertTrue(isValidCargoVendedor);

        // Testar cargo ESTOQUISTA (deve ser válido)
        boolean isValidCargoEstoquista = createEmployeeService.isValidCargo(Position.ESTOQUISTA);
        assertTrue(isValidCargoEstoquista);

        // Testar cargo inválido (deve ser inválido)
        boolean isValidInvalid = createEmployeeService.isValidCargo(null);
        assertFalse(isValidInvalid);

    }


}
