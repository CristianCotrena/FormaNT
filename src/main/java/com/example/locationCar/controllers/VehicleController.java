package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.dtos.EmployeeUpdateDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.services.vehicleService.CreateVehicleService;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.services.vehicleService.ListVehicleParamService;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import com.example.locationCar.services.vehicleService.UpdateVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/vehicle")
@Tag(name = "Vehicle", description = "Operations about vehicle")
public class VehicleController {
  
    private CreateVehicleService createVehicleService;
    private UpdateVehicleService updateVehicleService;
    private ListVehicleParamService listVehicleParamService;

    public VehicleController(ListVehicleParamService listVehicleParamService, CreateVehicleService createVehicleService, UpdateVehicleService updateVehicleService) {
        this.listVehicleParamService = listVehicleParamService;
        this.createVehicleService = createVehicleService;
        this.updateVehicleService = updateVehicleService;
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

    @PutMapping("/{id}")
    public ResponseEntity<BaseDto> updateVehicle (@PathVariable UUID id, @RequestBody VehicleInputDto vehicleInputDto){
        BaseDto updateVehicleId = updateVehicleService.updateVehicle(id, vehicleInputDto);
        return ResponseEntity.ok(updateVehicleId);
    }


}
