package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.vehicle.VehicleMockBuilder;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.SearchVehicleService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class SearchVehicleTest {

  @Mock private VehicleRepository vehicleRepository;

  @InjectMocks private SearchVehicleService searchVehicleService;

  private VehicleModel vehicleModel;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    vehicleModel = VehicleMockBuilder.build();
  }

  @Test
  public void testExecute_ValidationError() {
    String license = "ABC123";

    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), license);
    assertEquals(result.getErrors().size(), 1);
  }

  @Test
  public void testSearchVehicleByIdAndLicenseSuccess() {
    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(vehicleModel.getLicense()))
        .thenReturn(Optional.of(vehicleModel));

    BaseDto result =
        searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), vehicleModel.getLicense());
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByIdSuccess() {
    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), null);
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByLicenseSuccess() {
    when(vehicleRepository.findByLicense(vehicleModel.getLicense()))
        .thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(null, vehicleModel.getLicense());
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchNoParamsFailure() {
    when(vehicleRepository.findById(null)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(null)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(null, null);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByWrongIdRightLicenceFailure() {
    when(vehicleRepository.existsById(vehicleModel.getIdVehicle())).thenReturn(false);
    when(vehicleRepository.findByLicense(vehicleModel.getLicense()))
        .thenReturn(Optional.of(vehicleModel));

    BaseDto result =
        searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), vehicleModel.getLicense());
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByRightIdWrongLicenceFailure() {
    String wrongLicense = "HUR0925";

    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(wrongLicense)).thenReturn(Optional.empty());

    BaseDto result = searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), wrongLicense);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleInvalidLicenseFailure() {
    String license = "ABC123";

    when(vehicleRepository.findById(vehicleModel.getIdVehicle()))
        .thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByIdFailure() {
    when(vehicleRepository.existsById(vehicleModel.getIdVehicle())).thenReturn(false);

    BaseDto result = searchVehicleService.searchVehicle(vehicleModel.getIdVehicle(), null);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByLicenseFailure() {
    String license = "HUR0925";

    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.empty());

    BaseDto result = searchVehicleService.searchVehicle(null, license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }
}
