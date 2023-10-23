package com.example.locationCar.addressTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.address.AddressMockBuilder;
import com.example.locationCar.models.AddressModel;
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

  private AddressModel addressModel;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    deleteAddressService = new DeleteAddressService(addressRepository);
    addressModel = AddressMockBuilder.build();
  }

  @Test
  public void testDeleteAddress_Success() {
    when(addressRepository.findById(addressModel.getIdAddress()))
        .thenReturn(Optional.of(addressModel));
    when(addressRepository.save(addressModel)).thenReturn(addressModel);

    BaseDto result =
        deleteAddressService.deleteAddress(addressModel.getIdAddress().toString(), "", "");

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
    BaseDto result =
        deleteAddressService.deleteAddress(
            addressModel.getIdAddress().toString(),
            addressModel.getEmployee().getEmployeeId().toString(),
            "");

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Apenas um campo precisa ser enviado", result.getResult().getDescription());
  }
}
