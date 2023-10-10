package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.dtos.RentUpdateReturnDto;
import com.example.locationCar.dtos.input.RentInputDto;
import com.example.locationCar.services.rentService.CreateRentService;
import com.example.locationCar.services.rentService.UpdateRentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rent")
@Tag(name = "Rent", description = "Operations about rent")
public class RentController {

  private CreateRentService createRentService;
  private UpdateRentService updateRentService;

  public RentController(CreateRentService createRentService, UpdateRentService updateRentService) {
    this.createRentService = createRentService;
    this.updateRentService = updateRentService;
  }

  @Operation(summary = "Create Rent", description = "Add a rent to the database")
  @ApiResponse(
      responseCode = "201",
      description = "OK",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Aluguel criado com sucesso."))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Rent not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Bad Request")),
      })
  @PostMapping
  public ResponseEntity<BaseDto> createRent(@RequestBody RentInputDto rentInputDto) {
    BaseDto baseDto = createRentService.inserir(rentInputDto);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(summary = "Update rent", description = "Update rent")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = RentUpdateReturnDto.class))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not Found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Não encontrado")),
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid data",
      content = {
        @Content(
            mediaType = "application/json",
            schema =
                @Schema(
                    type = "string",
                    example = "Data de devolução precisa ser maior que o valor atual")),
      })
  @PutMapping("/{id}")
  public ResponseEntity<BaseDto> updateRent(
      @PathVariable String id, @RequestBody RentUpdateDto rentUpdateDto) {
    BaseDto baseDto = updateRentService.updateRent(id, rentUpdateDto);

    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }
}
