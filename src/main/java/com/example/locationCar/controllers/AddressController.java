package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.services.addressService.CreateAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private CreateAddressService createAddressService;

    public AddressController(CreateAddressService createAddressService) {
        this.createAddressService = createAddressService;
    }
    @Operation(summary = "Create address", description = "Add an address to the database")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @PostMapping
    public ResponseEntity<BaseDto> createAddress(@RequestBody AddressInputDto addressInputDto){
        BaseDto baseDto = createAddressService.inserir(addressInputDto);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }

}

