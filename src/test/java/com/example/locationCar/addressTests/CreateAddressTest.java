package com.example.locationCar.addressTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.services.addressService.CreateAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateAddressTest {

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private CreateAddressService createAddressService;

    private AddressInputDto dto;

    private ClientModel client;

    private EmployeeModel employee;

    @BeforeEach
    public void setup() {
        addressRepository = mock(AddressRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        clientRepository = mock(ClientRepository.class);
        createAddressService = new CreateAddressService(addressRepository, employeeRepository, clientRepository);

        dto = new AddressInputDto();
        dto.setCep("74840300");
        dto.setComplement("Casa de Esquina");
        dto.setNumber(12);

        client = new ClientModel();
        employee = new EmployeeModel();

    }

    @Test
    public void testCreateAddress_Success_with_client() {
        Long validLong = 3000L;

        dto.setIdClient(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.CREATED.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Endereço criado com sucesso", responseEntity.getResult().getDescription());
    }


    @Test
    public void testCreateAddress_Success_with_employee() {
        Long validLong = 3000L;

        dto.setIdEmployee(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.CREATED.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Endereço criado com sucesso", responseEntity.getResult().getDescription());
    }

    @Test
    public void testCreateAddress_given_idClient_And_IdEmployee_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdEmployee(3000L);
        dto.setIdClient(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(2, responseEntity.getErrors().size());
    }


    @Test
    public void testCreateAddress_given_emptyFields_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdClient(null);
        dto.setCep("");
        dto.setComplement("");
        dto.setNumber(null);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(4, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateAddress_given_InvalidCep_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdClient(3000L);
        dto.setCep("74840-300");

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }


    @Test
    public void testCreateAddress_withClientNotFound_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdClient(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateAddress_withEmployeeNotFound_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdEmployee(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateAddress_EmployeeAlreadyHasAnAddress_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdEmployee(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.empty());
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.of(new AddressModel()));
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateAddress_ClientAlreadyHasAnAddress_ShouldReturnError() {
        Long validLong = 3000L;

        dto.setIdClient(3000L);

        when(addressRepository.findByIdClient(any(ClientModel.class))).thenReturn(Optional.of(new AddressModel()));
        when(addressRepository.findByIdEmployee(any(EmployeeModel.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        AddressModel address = new AddressModel();
        address.setIdAddress(validLong);

        when(addressRepository.save(any(AddressModel.class))).thenReturn(address);

        BaseDto responseEntity = createAddressService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }
}
