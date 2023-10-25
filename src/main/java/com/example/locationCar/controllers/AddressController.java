package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.constants.ErrorMessage;



import com.example.locationCar.dtos.DeleteAddressDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.services.addressService.CreateAddressService;
import com.example.locationCar.services.addressService.DeleteAddressService;
import com.example.locationCar.services.addressService.SearchAddressService;
import com.example.locationCar.dtos.AddressUpdateDto;
import com.example.locationCar.services.addressService.UpdateAddressService;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/address")
@Tag(name = "Address", description = "Operations about address")
//@AllArgsConstructor
public class AddressController {

  private final SearchAddressService searchAddressService;
  private final CreateAddressService createAddressService;
  private final DeleteAddressService deleteAddressService;
  private final UpdateAddressService updateAddressService;

    public AddressController(SearchAddressService searchAddressService,
                             CreateAddressService createAddressService,
                             DeleteAddressService deleteAddressService,
                             UpdateAddressService updateAddressService) {
        this.searchAddressService = searchAddressService;
        this.createAddressService = createAddressService;
        this.deleteAddressService = deleteAddressService;
        this.updateAddressService = updateAddressService;
    }

    @Operation(summary = "Search Address", description = "Search an address from database")
  @ApiResponse(
      responseCode = "400",
      description = "Bad Request ",
      content = {
        @Content(
            mediaType = "text/plain",
            schema =
                @Schema(
                    type = "string",
                    example = "Estes dados não são permitidos serem passados juntos"))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Address not found",
      content = {
        @Content(
            mediaType = "text/plain",
            schema = @Schema(type = "string", example = "Não encontrado"))
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
          @RequestParam(value = "idAddress", required = false)
          String idAddress,
      @Schema(description = "Client ID you created in POST.")
          @RequestParam(value = "idClient", required = false)
          String idClient,
      @Schema(description = "Employee ID you created in POST.")
          @RequestParam(value = "idEmployee", required = false)
          String idEmployee) {
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
        errors.add(
            new BaseErrorDto(
                "address", ErrorMessage.AT_LEAST_ONE + "idAddress, idClient e idEmployee"));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
        return result.get();
      }
    } catch (IllegalArgumentException e) {
      errors.add(new BaseErrorDto("address", ErrorMessage.INVALID_FIELD));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.NOT_FOUND, errors);
      return result.get();
    }
  }

  @Operation(summary = "Create address", description = "Add an address to the database")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Invalid data")
  @PostMapping
  public ResponseEntity<BaseDto> createAddress(@RequestBody AddressInputDto addressInputDto) {
    BaseDto baseDto = createAddressService.inserir(addressInputDto);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(
      summary = "Delete address",
      description = "Logically delete an address in the database")
  @ApiResponse(
      responseCode = "200",
      description = "Deletado com sucesso.",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DeleteAddressDto.class))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Apenas um campo precisa ser enviado",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Apenas um campo precisa ser enviado"))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not Found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Not Found"))
      })
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
