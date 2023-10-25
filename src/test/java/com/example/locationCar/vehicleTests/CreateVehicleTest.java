package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.mock.vehicle.VehicleInputDtoMockBuilder;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

public class CreateVehicleTest {

  @MockBean private VehicleRepository vehicleRepository;

  @Autowired private CreateVehicleService createVehicleService;

  private VehicleInputDto dto;

  @BeforeEach
  public void setup() {
    vehicleRepository = mock(VehicleRepository.class);
    createVehicleService = new CreateVehicleService(vehicleRepository);

    dto = VehicleInputDtoMockBuilder.build();
  }

  @Test
  public void testCreateVehicle_Success() {
    UUID validUUID = UUID.randomUUID();

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.CREATED.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Veículo criado com sucesso", responseEntity.getResult().getDescription());
  }

  @Test
  public void testCreateVehicle_LicensePlateAlreadyExists_Error() {
    UUID validUUID = UUID.randomUUID();

    when(vehicleRepository.findByLicense(any(String.class)))
        .thenReturn(Optional.of(new VehicleModel()));
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
  }

  @Test
  public void testCreateVehicle_EmptyFields() {
    UUID validUUID = UUID.randomUUID();
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);

    dto.setColor("");
    dto.setFuel("");
    dto.setModel("");

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
    assertEquals(3, responseEntity.getErrors().size());
  }

  @Test
  public void testCreateVehicle_DailyValueLessThanZero() {
    UUID validUUID = UUID.randomUUID();
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);

    dto.setDailyValue(BigDecimal.valueOf(-50));

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
    assertEquals(1, responseEntity.getErrors().size());
  }

  @Test
  public void testCreateVehicle_InvalidLicense() {
    UUID validUUID = UUID.randomUUID();
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);
    dto.setLicense("ABC12");

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
    assertEquals(1, responseEntity.getErrors().size());
  }

  @Test
  public void testCreateVehicle_InvalidDoorNumber() {
    UUID validUUID = UUID.randomUUID();
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validUUID);

    dto.setDoorNumber(3);

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
    assertEquals(1, responseEntity.getErrors().size());
  }
}
