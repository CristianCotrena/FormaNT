package com.example.locationCar.addressTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.controllers.AddressController;
import com.example.locationCar.dtos.AddressUpdateDto;
import com.example.locationCar.services.addressService.UpdateAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UpdateAddressTest {

    @Mock
    private UpdateAddressService updateAddressService;

    @InjectMocks
    private AddressController addressController;

    private AddressUpdateDto addressUpdateDto;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        addressUpdateDto =
                new AddressUpdateDto(null,
                        "Sim",
                        "Bage",
                        457,
                        "Loja",
                        "Cachoeirinha",
                        "RS",
                        "Brasil",
                        "94945-000");
    }

    @Test
    public void testUpdateAddress_Success() {
        UUID addressId = UUID.fromString("99edbcb8-f974-479a-a3bf-f3ab8da7b9af");

        when(updateAddressService.updateAddress(addressId, addressUpdateDto))
                .thenReturn(new ResponseSuccessBuilder<AddressUpdateDto>(HttpStatus.OK, new AddressUpdateDto(addressId.toString()),
                        SuccessMessage.UPDATE_ADDRESS).get());

        BaseDto<Void> updateAddress = addressController.updateAddress(addressId.toString(), addressUpdateDto);

        assertEquals(HttpStatus.OK.value(), updateAddress.getResult().getStatusCode());
        assertNotNull(updateAddress.getData());
    }

    @Test
    public void testUpdateAddress_InvalidFields() {
        UUID addressId = UUID.fromString("99edbcb8-f974-479a-a3bf-f3ab8da7b9af");

        AddressUpdateDto addressUpdateDto = new AddressUpdateDto();

        when(updateAddressService.updateAddress(addressId, addressUpdateDto))
                .thenThrow(new IllegalArgumentException("Campo inválido"));

        BaseDto<Void> baseDto =
                addressController.updateAddress(addressId.toString(), addressUpdateDto);

        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), baseDto.getResult().getStatusCode());
        assertEquals(ErrorMessage.INVALID_FIELD, baseDto.getErrors().get(0).getMessage());
    }

    @Test
    public void testUpdateAddressEmpty() {
        UUID addressId = UUID.fromString("4c38c805-e0a4-4b19-ae13-582cbcaac807");
        List<BaseErrorDto> notFoundErrors = List.of(new BaseErrorDto("Address", ErrorMessage.NOT_FOUND));

        when(updateAddressService.updateAddress(addressId, addressUpdateDto))
                .thenReturn(new ResponseErrorBuilder(HttpStatus.NOT_FOUND, notFoundErrors).get());

        BaseDto<Void> baseDto =
                addressController.updateAddress(addressId.toString(), addressUpdateDto);

        assertEquals(HttpStatus.NOT_FOUND.value(), baseDto.getResult().getStatusCode());
        assertEquals(ErrorMessage.NOT_FOUND, baseDto.getErrors().get(0).getMessage());
    }
}
