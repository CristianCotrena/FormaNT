package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import com.example.locationCar.services.vehicleService.DeleteVehicleService;
import com.example.locationCar.services.vehicleService.ListVehicleParamService;
import com.example.locationCar.services.vehicleService.UpdateVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vehicle")
@Tag(name = "Vehicle", description = "Operations about vehicle")
public class VehicleController {
  
    private CreateVehicleService createVehicleService;
    private UpdateVehicleService updateVehicleService;
    private ListVehicleParamService listVehicleParamService;
    private DeleteVehicleService deleteVehicleService;

    public VehicleController(ListVehicleParamService listVehicleParamService, CreateVehicleService createVehicleService, UpdateVehicleService updateVehicleService, DeleteVehicleService deleteVehicleService) {
        this.listVehicleParamService = listVehicleParamService;
        this.createVehicleService = createVehicleService;
        this.updateVehicleService = updateVehicleService;
        this.deleteVehicleService = deleteVehicleService;
    }


  public VehicleController(
      ListVehicleParamService listVehicleParamService,
      CreateVehicleService createVehicleService,
      DeleteVehicleService deleteVehicleService) {
    this.listVehicleParamService = listVehicleParamService;
    this.createVehicleService = createVehicleService;
    this.deleteVehicleService = deleteVehicleService;
  }

  @Operation(summary = "List vehicles Param", description = "List vehicles Param")
  @ApiResponse(responseCode = "200", description = "Found")
  @ApiResponse(responseCode = "400", description = "Invalid data")
  @GetMapping("/list")
  public ResponseEntity<BaseDto> searchCars(
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Double rating,
      @RequestParam(required = false) Double max,
      @RequestParam(required = false) Double min,
      Pageable pageable) {
    BaseDto baseDto = listVehicleParamService.listVehicles(pageable, color, rating, max, min);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

  @Operation(summary = "Create vehicle", description = "Add a vehicle to the database")
  @ApiResponse(responseCode = "201", description = "Created")
  @ApiResponse(responseCode = "400", description = "Invalid data")
  @PostMapping
  public ResponseEntity<BaseDto> createVehicle(@RequestBody VehicleInputDto vehicleInputDto) {
    BaseDto baseDto = createVehicleService.inserir(vehicleInputDto);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }

    @Operation(summary = "Delete Vehicle", description = "Delete an vehicle to database")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Deletado com sucesso."))
    })
    @ApiResponse(responseCode = "404", description = "Vehicle not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Não foi possível localizar na base de dados com os seguinte parâmetros:")),
    })
    @DeleteMapping("/")
    public ResponseEntity<BaseDto> deleteVehicle (
            @RequestParam (required = false) UUID idVehicle,
            @RequestParam (required = false) String license
    ) {
        BaseDto baseDto = deleteVehicleService.execute(idVehicle, license);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }

    @Operation(summary = "Update vehicle", description = "Update vehicle")
    @ApiResponse(responseCode = "200", description = "Updated", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", format = "uuid"))
    })
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "vehicle não encontrado"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseDto> updateVehicle (@PathVariable UUID id, @RequestBody VehicleInputDto vehicleInputDto){
        BaseDto updateVehicleId = updateVehicleService.updateVehicle(id, vehicleInputDto);
        return ResponseEntity.ok(updateVehicleId);
    }

  @Operation(summary = "Delete Vehicle", description = "Delete an vehicle to database")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Deletado com sucesso."))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Vehicle not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema =
                @Schema(
                    type = "string",
                    example =
                        "Não foi possível localizar na base de dados com os seguinte parâmetros:")),
      })
  @DeleteMapping("/")
  public ResponseEntity<BaseDto> deleteVehicle(
      @RequestParam(required = false) UUID idVehicle,
      @RequestParam(required = false) String license) {
    BaseDto baseDto = deleteVehicleService.execute(idVehicle, license);
    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }
}
