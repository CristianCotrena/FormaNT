package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.dtos.AddressUpdateDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.services.addressService.CreateAddressService;
import com.example.locationCar.services.addressService.UpdateAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private CreateAddressService createAddressService;
    private UpdateAddressService updateAddressService;

    public AddressController(CreateAddressService createAddressService, UpdateAddressService updateAddressService) {
        this.createAddressService = createAddressService;
        this.updateAddressService = updateAddressService;
    }

    @Operation(summary = "Create address", description = "Add an address to the database")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @PostMapping
    public ResponseEntity<BaseDto> createAddress(@RequestBody AddressInputDto addressInputDto) {
        BaseDto baseDto = createAddressService.inserir(addressInputDto);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }

    @Operation(summary = "Update address", description = "Update address")
    @ApiResponse(
            responseCode = "200",
            description = "Updated",
            content = {
                    @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
            })
    @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = {
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Não encontrado"))
            })
    @ApiResponse(
            responseCode = "412",
            description = "Invalid data",
            content = {
                    @Content(
                            mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "Campo inválido")),
            })
    @PutMapping("/{id}")
    public BaseDto<Void> updateAddress(@PathVariable String id, @RequestBody AddressUpdateDto addressUpdateDto) {
        List<BaseErrorDto> errors = new ArrayList<>();
        try {
            UUID idAddress = UUID.fromString(id);
            BaseDto updatedAddressId = updateAddressService.updateAddress(idAddress, addressUpdateDto);
            return updatedAddressId;
        } catch (IllegalArgumentException e) {
            errors.add(new BaseErrorDto("idAddress", ErrorMessage.INVALID_FIELD));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.PRECONDITION_FAILED, errors);
            return result.get();
        }
    }
}

