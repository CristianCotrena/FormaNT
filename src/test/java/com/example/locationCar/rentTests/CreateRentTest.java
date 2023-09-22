package com.example.locationCar.rentTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.RentInputDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.rentService.CreateRentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateRentTest {

    @MockBean
    private RentRepository rentRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private CreateRentService createRentService;

    private RentInputDto dto;

    private ClientModel client;

    private EmployeeModel employee;

    private VehicleModel vehicle;

    @BeforeEach
    public void setup() {
        rentRepository = mock(RentRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        clientRepository = mock(ClientRepository.class);
        vehicleRepository = mock(VehicleRepository.class);
        createRentService = new CreateRentService(clientRepository,employeeRepository,vehicleRepository,rentRepository);

        dto = new RentInputDto();
        dto.setIdClient(UUID.fromString("f330b8fb-4e38-44f5-a096-11fbc43d07d3"));
        dto.setIdEmployee(UUID.fromString("0ca1f321-06ad-4845-845d-4c09a7a1b48e"));
        dto.setIdVehicle(UUID.fromString("cf28f96b-0fb0-4af9-9102-de2aa9e6cda7"));
        dto.setContractingDate(ZonedDateTime.now());
        dto.setReturnDate(ZonedDateTime.now().plusDays(30));
        dto.setHaveInsurance(0);
        dto.setStatus(1);

        client = new ClientModel();
        employee = new EmployeeModel();
        vehicle = new VehicleModel();
    }

    @Test
    public void testCreateRent_Success() {
        UUID validUUID = UUID.randomUUID();

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.CREATED.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Aluguel criado com sucesso", responseEntity.getResult().getDescription());
    }

    @Test
    public void testCreateRent_with_ClientNotFound_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }


    @Test
    public void testCreateRent_with_EmployeeNotFound_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateRent_with_VehicleNotFound_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }


    @Test
    public void testCreateRent_given_ContractingDateGreaterThanReturnDate_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();
        dto.setContractingDate(ZonedDateTime.now().plusDays(50));

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(2, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateRent_with_ContractedVehicle_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.of(new RentModel()));
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }

    @Test
    public void testCreateRent_given_pastContractingDate_ShouldReturnError() {
        UUID validUUID = UUID.randomUUID();
        dto.setContractingDate(ZonedDateTime.now().minusDays(50));

        when(rentRepository.findByVehicleAndContractingDateLessThanEqualAndReturnDateGreaterThanEqual(any(VehicleModel.class),any(ZonedDateTime.class),any(ZonedDateTime.class))).thenReturn(Optional.empty());
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.of(vehicle));

        RentModel rent = new RentModel();
        rent.setIdRent(validUUID);

        when(rentRepository.save(any(RentModel.class))).thenReturn(rent);

        BaseDto responseEntity = createRentService.inserir(dto);

        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
        assertEquals("Bad Request", responseEntity.getResult().getDescription());
        assertEquals(1, responseEntity.getErrors().size());
    }
}
