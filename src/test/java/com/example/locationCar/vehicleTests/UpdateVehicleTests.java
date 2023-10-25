package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.mock.vehicle.VehicleInputDtoMockBuilder;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.UpdateVehicleService;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class UpdateVehicleTests {

  @MockBean private VehicleRepository vehicleRepository;

  @Autowired private UpdateVehicleService updateVehicleService;
  private VehicleInputDto dto;

  @BeforeEach
  public void setUp() {

    MockitoAnnotations.openMocks(this);

    dto = VehicleInputDtoMockBuilder.build();
  }

  @Test
  public void updateVehicle_success_updateColor() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setColor("Black");

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateFuel() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setFuel("Gasolina");

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateMileage() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setMileage(BigDecimal.valueOf(50));

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateModel() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setModel("UNO");

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateLicence() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setLicense("DRI1A20");

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateRating() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setRating(BigDecimal.valueOf(50));

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateBrand() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setBrand("Fiat");

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateDoor() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setDoorNumber(4);

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void updateVehicle_success_updateDailyValuegit() {
    UUID vehicleId = UUID.randomUUID();
    VehicleModel existingVehicle = new VehicleModel();
    existingVehicle.setDailyValue(BigDecimal.valueOf(100));

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
    when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

    BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
  }
}
