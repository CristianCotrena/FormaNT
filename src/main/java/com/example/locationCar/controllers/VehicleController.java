package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.dtos.DeleteVehicleDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.services.vehicleService.DeleteVehicleService;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/vehicle")
@Tag(name = "Vehicle", description = "Operations about vehicle")
public class VehicleController {
  
    private CreateVehicleService createVehicleService;

    private final ListVehicleService listVehicleService;

    private DeleteVehicleService deleteVehicleService;

    public VehicleController(ListVehicleService listVehicleService, CreateVehicleService createVehicleService, DeleteVehicleService deleteVehicleService) {
        this.listVehicleService = listVehicleService;
        this.createVehicleService = createVehicleService;
        this.deleteVehicleService = deleteVehicleService;
    }

    @Operation(summary = "List vehicles", description = "List vehicles3")
    @ApiResponse(responseCode = "200", description = "Found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleModel.class))
    })
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Veículos não encontrados.")),
    })
    @GetMapping("/list")
    public ResponseEntity<BaseDto> listVehicles(@RequestParam(required = false) String page) {
        BaseDto baseDto = listVehicleService.listVehicles(page);

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
}
