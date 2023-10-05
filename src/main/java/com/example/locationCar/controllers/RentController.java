package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.dtos.RentUpdateReturnDto;
import com.example.locationCar.services.rentService.UpdateRentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rent")
@Tag(name = "Rent", description = "Operations about rent")
public class RentController {

  private UpdateRentService updateRentService;

  public RentController(UpdateRentService updateRentService) {
    this.updateRentService = updateRentService;
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
