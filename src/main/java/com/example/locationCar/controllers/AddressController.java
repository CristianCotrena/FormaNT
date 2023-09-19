package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.services.addressService.SearchAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/address")
@Tag(name = "Address", description = "Operations about address")
public class AddressController {

    private final SearchAddressService searchAddressService;

    public AddressController(SearchAddressService searchAddressService) {
        this.searchAddressService = searchAddressService;
    }

    @Operation(summary = "Search Address", description = "Search an address from database")
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request ",
            content = {
        @Content(
                mediaType = "text/plain",
                schema =
                @Schema(type = "string", example = "Estes dados não são permitidos serem passados juntos"))
    })
    @ApiResponse(
            responseCode = "404",
            description = "Address not found",
            content = {
                    @Content(
                            mediaType = "text/plain",
                            schema =
                            @Schema(type = "string", example = "Não encontrado"))
            })
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
                    @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid")),
            })
    @GetMapping
    public BaseDto<Void> searchAddress(
            @Schema(description = "Address ID you created in POST.")
            @RequestParam(value = "idAddress", required = false) String idAddress,
            @Schema(description = "Client ID you created in POST.")
            @RequestParam(value = "idClient", required = false) String idClient,
            @Schema(description = "Employee ID you created in POST.")
            @RequestParam(value = "idEmployee", required = false) String idEmployee
    ) {
        List<BaseErrorDto> errors = new ArrayList<>();
        try {
            if (idClient != null && idEmployee != null) {
                errors.add(new BaseErrorDto("idClient e idEmployee", ErrorMessage.CONFLIT));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
                return result.get();
            } else if (idAddress != null && idClient != null) {
                errors.add(new BaseErrorDto("idAddress e idClient", ErrorMessage.CONFLIT));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
                return result.get();
            } else if (idAddress != null && idEmployee != null) {
                errors.add(new BaseErrorDto("idAddress e idEmployee", ErrorMessage.CONFLIT));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
                return result.get();
            } else if (idAddress != null) {
                UUID uuidAddress = UUID.fromString(idAddress);
                BaseDto addressModel = searchAddressService.findAddressById(uuidAddress);
                return addressModel;
            } else if (idClient != null) {
                UUID uuidClient = UUID.fromString(idClient);
                BaseDto addressModel = searchAddressService.findAddressByIdClient(uuidClient);
                return addressModel;
            } else if (idEmployee != null) {
                UUID uuidEmployee = UUID.fromString(idEmployee);
                BaseDto addressModel = searchAddressService.findAddressByIdEmployee(uuidEmployee);
                return addressModel;
            } else {
                errors.add(new BaseErrorDto("address", ErrorMessage.AT_LEAST_ONE + "idAddress, idClient e idEmployee"));
                ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
                return result.get();
            }
        } catch (IllegalArgumentException e) {
            errors.add(new BaseErrorDto("address", ErrorMessage.INVALID_FIELD));
            ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
            return result.get();
        }
    }
}


