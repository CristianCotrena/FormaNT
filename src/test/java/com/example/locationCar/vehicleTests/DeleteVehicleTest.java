package com.example.locationCar.vehicleTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.DeleteVehicleService;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

public class DeleteVehicleTest {

  @Mock private VehicleRepository vehicleRepository;

  @InjectMocks private DeleteVehicleService deleteVehicleService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testExecute_ValidationError() {

    BaseDto result = deleteVehicleService.execute(null, null);
    assertEquals(result.getErrors().size(), 1);
  }

  @Test
  public void testExecute_DeleteByIdAndLicense_Success() {

    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.existsByLicense(license)).thenReturn(Optional.of(true));

    BaseDto result = deleteVehicleService.execute(idVehicle, license);
    Integer integer = HttpStatus.ACCEPTED.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteById_Success() {

    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.existsById(idVehicle)).thenReturn(true);

    BaseDto result = deleteVehicleService.execute(idVehicle, null);
    Integer integer = HttpStatus.ACCEPTED.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteById_Failure() {

    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.existsById(idVehicle)).thenReturn(false);

    BaseDto result = deleteVehicleService.execute(idVehicle, null);
    Integer integer = HttpStatus.BAD_REQUEST.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteByLicense_Success() {

    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.existsByLicense(license)).thenReturn(Optional.of(true));

    BaseDto result = deleteVehicleService.execute(null, license);
    Integer integer = HttpStatus.ACCEPTED.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteByLicense_Failure() {

    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.existsByLicense(license)).thenReturn(Optional.of(false));

    BaseDto result = deleteVehicleService.execute(null, license);
    Integer integer = HttpStatus.BAD_REQUEST.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }
}
