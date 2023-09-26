package com.example.locationCar.addressTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.services.addressService.DeleteAddressService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class DeleteAddressTest {
  @Mock private AddressRepository addressRepository;

  @InjectMocks private DeleteAddressService deleteAddressService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    deleteAddressService = new DeleteAddressService(addressRepository);
  }

  @Test
  public void testDeleteAddress_Success() {
    UUID addressId = UUID.randomUUID();
    UUID employeeId = UUID.randomUUID();
    EmployeeModel employee = new EmployeeModel();
    AddressModel address = new AddressModel();
    employee.setEmployeeId(employeeId);
    employee.setStatus(1);
    address.setIdAddress(addressId);
    address.setStatus(1);

    when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
    when(addressRepository.save(address)).thenReturn(address);

    BaseDto result = deleteAddressService.deleteAddress(addressId.toString(), "", "");

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Deletado com sucesso.", result.getResult().getDescription());
  }

  @Test
  public void testDeleteAddress_NotFound() {
    UUID addressId = UUID.randomUUID();
    when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

    BaseDto result = deleteAddressService.deleteAddress(addressId.toString(), "", "");

    assertEquals(HttpStatus.NOT_FOUND.value(), result.getResult().getStatusCode());
    assertEquals("Not Found", result.getResult().getDescription());
  }

  @Test
  public void testDeleteAddress_MoreThanOneFieldSent() {
    UUID addressId = UUID.randomUUID();
    UUID clientId = UUID.randomUUID();

    BaseDto result =
        deleteAddressService.deleteAddress(addressId.toString(), "", clientId.toString());

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Apenas um campo precisa ser enviado", result.getResult().getDescription());
  }
}
