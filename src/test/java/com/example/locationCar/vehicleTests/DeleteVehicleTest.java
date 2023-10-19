package com.example.locationCar.vehicleTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.vehicle.VehicleMockBuilder;
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

  private VehicleModel vehicleModel;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    vehicleModel = VehicleMockBuilder.build();
  }

  @Test
  public void testExecute_ValidationError() {
    BaseDto result = deleteVehicleService.execute(null, null);
    assertEquals(result.getErrors().size(), 1);
  }

  @Test
  public void testExecute_DeleteByIdAndLicense_Success() {
    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.existsByLicense(vehicleModel.getLicense()))
        .thenReturn(Optional.of(true));

    BaseDto result =
        deleteVehicleService.execute(vehicleModel.getIdVehicle(), vehicleModel.getLicense());
    Integer integer = HttpStatus.ACCEPTED.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteById_Success() {
    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.existsById(vehicleModel.getIdVehicle())).thenReturn(true);

    BaseDto result = deleteVehicleService.execute(vehicleModel.getIdVehicle(), null);
    Integer integer = HttpStatus.ACCEPTED.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteById_Failure() {
    when(vehicleRepository.existsById(vehicleModel.getIdVehicle())).thenReturn(false);

    BaseDto result = deleteVehicleService.execute(vehicleModel.getIdVehicle(), null);
    Integer integer = HttpStatus.BAD_REQUEST.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }

  @Test
  public void testExecute_DeleteByLicense_Failure() {
    when(vehicleRepository.existsByLicense(vehicleModel.getLicense()))
        .thenReturn(Optional.of(false));

    BaseDto result = deleteVehicleService.execute(null, vehicleModel.getLicense());
    Integer integer = HttpStatus.BAD_REQUEST.value();
    assertEquals(result.getResult().getStatusCode(), integer);
  }
}
