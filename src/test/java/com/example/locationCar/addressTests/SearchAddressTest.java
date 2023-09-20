package com.example.locationCar.addressTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.services.addressService.SearchAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchAddressTest {

  @InjectMocks
  private SearchAddressService searchAddressService;

  @Mock private AddressRepository addressRepository;

  private AddressModel addressModel;
  private ClientModel clientModel;
  private EmployeeModel employeeModel;

  @BeforeEach
  void setup() {
    addressModel = new AddressModel();
    addressModel.setIdAddress(UUID.randomUUID());
    addressModel.setCep("94945-010");
    addressModel.setCity("Cachoeirinha");
    addressModel.setComplement("casa");
    addressModel.setCountry("Brasil");
    addressModel.setNumber(121);
    addressModel.setPublicPlace("Sim");
    addressModel.setState("RS");
    addressModel.setStatus(1);

    clientModel = new ClientModel();
    clientModel.setIdClient(UUID.randomUUID());
    addressModel.setClient(clientModel);

    employeeModel = new EmployeeModel();
    employeeModel.setEmployeeId(UUID.randomUUID());
    addressModel.setEmployee(employeeModel);
  }

  @Test
  public void testGetAddressByIdAddress() {
    UUID id = addressModel.getIdAddress();

    when(addressRepository.findById(id)).thenReturn(Optional.of(addressModel));

    BaseDto result = searchAddressService.findAddressById(id);

    assertNotNull(result);
    assertNotEquals(addressModel, result.getData());
  }

  @Test
  public void testGetAddressByIdClient() {
    ClientModel client = addressModel.getClient();

    when(addressRepository.findByIdClient(client.getIdClient())).thenReturn(Optional.of(addressModel));

    BaseDto result = searchAddressService.findAddressByIdClient(client.getIdClient());

    assertNotNull(result);
    assertNotEquals(addressModel, result.getData());
  }

  @Test
  public void testGetAddressByEmployeeId() {
    EmployeeModel employee = addressModel.getEmployee();

    when(addressRepository.findByEmployeeId(employee.getEmployeeId())).thenReturn(Optional.of(addressModel));

    BaseDto result = searchAddressService.findAddressByIdEmployee(employee.getEmployeeId());

    assertNotNull(result);
    assertNotEquals(addressModel, result.getData());
  }
}
