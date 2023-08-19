package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/v1/vehicle")
@Tag(name = "Vehicle", description = "Operations about vehicle")
public class VehicleController {
  
    private CreateVehicleService createVehicleService;

    private final ListVehicleService listVehicleService;

    public VehicleController(ListVehicleService listVehicleService, CreateVehicleService createVehicleService) {
        this.listVehicleService = listVehicleService;
        this.createVehicleService = createVehicleService;
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


}
