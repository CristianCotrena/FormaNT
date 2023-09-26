package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.SearchVehicleService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class SearchVehicleTest {

  @Mock private VehicleRepository vehicleRepository;

  @InjectMocks private SearchVehicleService searchVehicleService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testExecute_ValidationError() {
    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, license);
    assertEquals(result.getErrors().size(), 1);
  }

  @Test
  public void testSearchVehicleByIdAndLicenseSuccess() {
    UUID idVehicle = UUID.randomUUID();
    String license = "HUR0925";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, license);
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByIdSuccess() {
    UUID idVehicle = UUID.randomUUID();

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, null);
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByLicenseSuccess() {
    String license = "HUR0925";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setLicense(license);

    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(null, license);
    Integer expectedStatusCode = HttpStatus.OK.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchNoParamsFailure() {
    UUID idVehicle = null;
    String license = null;

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByWrongIdRightLicenceFailure() {
    UUID idVehicle = UUID.randomUUID();
    String license = "HUR0925";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.existsById(idVehicle)).thenReturn(false);
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByRightIdWrongLicenceFailure() {
    UUID idVehicle = UUID.randomUUID();
    String correctLicense = "ABC123";
    String wrongLicense = "HUR0925";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(correctLicense);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(wrongLicense)).thenReturn(Optional.empty());

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, wrongLicense);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleInvalidLicenseFailure() {
    UUID idVehicle = UUID.randomUUID();
    String license = "ABC123";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);
    vehicleModel.setLicense(license);

    when(vehicleRepository.findById(idVehicle)).thenReturn(Optional.of(vehicleModel));
    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.of(vehicleModel));

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByIdFailure() {
    UUID idVehicle = UUID.randomUUID();

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setIdVehicle(idVehicle);

    when(vehicleRepository.existsById(idVehicle)).thenReturn(false);

    BaseDto result = searchVehicleService.searchVehicle(idVehicle, null);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  public void testSearchVehicleByLicenseFailure() {
    String license = "HUR0925";

    VehicleModel vehicleModel = new VehicleModel();
    vehicleModel.setLicense(license);

    when(vehicleRepository.findByLicense(license)).thenReturn(Optional.empty());

    BaseDto result = searchVehicleService.searchVehicle(null, license);
    Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
    Integer actualStatusCode = result.getResult().getStatusCode();
    assertEquals(expectedStatusCode, actualStatusCode);
  }
}
