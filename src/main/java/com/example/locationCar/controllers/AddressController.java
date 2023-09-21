package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.services.addressService.CreateAddressService;
import com.example.locationCar.services.addressService.DeleteAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

  private CreateAddressService createAddressService;
  private DeleteAddressService deleteAddressService;

  public AddressController(
      CreateAddressService createAddressService, DeleteAddressService deleteAddressService) {
    this.createAddressService = createAddressService;
    this.deleteAddressService = deleteAddressService;
  }

  @Operation(summary = "Create address", description = "Add an address to the database")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Invalid data")
  @PostMapping
  public ResponseEntity<BaseDto> createAddress(@RequestBody AddressInputDto addressInputDto) {
    BaseDto baseDto = createAddressService.inserir(addressInputDto);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteAddress(
      @RequestParam(value = "idAddress", required = false) String idAddress,
      @RequestParam(value = "idEmployee", required = false) String idEmployee,
      @RequestParam(value = "idClient", required = false) String idClient) {
    BaseDto baseDto =
        deleteAddressService.deleteAddress(
            idAddress != null ? idAddress : "",
            idEmployee != null ? idEmployee : "",
            idClient != null ? idClient : "");

    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }
}
