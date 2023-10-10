package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import java.math.BigDecimal;
import java.util.Optional;
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

    dto = new VehicleInputDto();
    dto.setBrand("Tesla");
    dto.setColor("Black");
    dto.setFuel("Eletric");
    dto.setModel("s");
    dto.setLicense("BRA2E25");
    dto.setDoorNumber(4);
    dto.setMileage(BigDecimal.valueOf(300.5));
    dto.setDailyValue(BigDecimal.valueOf(50.3));
    dto.setRating(BigDecimal.valueOf(4.5));
  }

  @Test
  public void testCreateVehicle_Success() {
    Long validLong = 3000L;

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.CREATED.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Veículo criado com sucesso", responseEntity.getResult().getDescription());
  }

  @Test
  public void testCreateVehicle_LicensePlateAlreadyExists_Error() {
    Long validLong = 3000L;

    when(vehicleRepository.findByLicense(any(String.class)))
        .thenReturn(Optional.of(new VehicleModel()));
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
  }

  @Test
  public void testCreateVehicle_EmptyFields() {
    Long validLong = 3000L;
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);

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
    Long validLong = 3000L;
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);

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
    Long validLong = 3000L;
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);
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
    Long validLong = 3000L;
    VehicleModel vehicle = new VehicleModel();
    vehicle.setIdVehicle(validLong);

    dto.setDoorNumber(3);

    when(vehicleRepository.findByLicense(any(String.class))).thenReturn(Optional.empty());
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(vehicle);

    BaseDto responseEntity = createVehicleService.inserir(dto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
    assertEquals(1, responseEntity.getErrors().size());
  }
}
