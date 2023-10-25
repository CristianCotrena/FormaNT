package com.example.locationCar.addressTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.address.AddressMockBuilder;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.services.addressService.SearchAddressService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchAddressTest {

  @InjectMocks private SearchAddressService searchAddressService;

  @Mock private AddressRepository addressRepository;

  private AddressModel addressModel;

  @BeforeEach
  void setup() {
    addressModel = AddressMockBuilder.build();
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

    when(addressRepository.findByClientIdClient(client.getIdClient()))
        .thenReturn(Optional.of(addressModel));

    BaseDto result = searchAddressService.findAddressByIdClient(client.getIdClient());

    assertNotNull(result);
    assertNotEquals(addressModel, result.getData());
  }

  @Test
  public void testGetAddressByEmployeeId() {
    EmployeeModel employee = addressModel.getEmployee();

    when(addressRepository.findByEmployeeEmployeeId(employee.getEmployeeId()))
        .thenReturn(Optional.of(addressModel));

    BaseDto result = searchAddressService.findAddressByIdEmployee(employee.getEmployeeId());

    assertNotNull(result);
    assertNotEquals(addressModel, result.getData());
  }
}
