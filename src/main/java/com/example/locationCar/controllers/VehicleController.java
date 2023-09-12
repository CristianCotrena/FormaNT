package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.dtos.DeleteVehicleDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.services.vehicleService.*;
import com.example.locationCar.models.VehicleModel;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/vehicle")
@Tag(name = "Vehicle", description = "Operations about vehicle")
public class VehicleController {

    private CreateVehicleService createVehicleService;

    private ListVehicleParamService listVehicleParamService;

    private DeleteVehicleService deleteVehicleService;

    private SearchVehicleService searchVehicleService;

    public VehicleController(ListVehicleParamService listVehicleParamService, CreateVehicleService createVehicleService, DeleteVehicleService deleteVehicleService, SearchVehicleService searchVehicleService) {
        this.listVehicleParamService = listVehicleParamService;
        this.createVehicleService = createVehicleService;
        this.deleteVehicleService = deleteVehicleService;
        this.searchVehicleService = searchVehicleService;
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
    public ResponseEntity<BaseDto> deleteVehicle(
            @RequestParam(required = false) UUID idVehicle,
            @RequestParam(required = false) String license
    ) {
        BaseDto baseDto = deleteVehicleService.execute(idVehicle, license);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }

    @GetMapping
    public ResponseEntity<Object> searchVehicle(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String license
    ) {
        try {
            if (id != null) {
                VehicleModel vehicleModel = searchVehicleService.searchVehicleById(id);
                return ResponseEntity.ok(vehicleModel);
            } else if (license != null) {
                VehicleModel vehicleModel = searchVehicleService.searchVehicleByLicense(license);
                return ResponseEntity.ok(vehicleModel);
            } else {
                return ResponseEntity.badRequest().body("Informe um ID ou placa válidos para buscar o veículo.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Informe um ID ou placa válidos para buscar o veículo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }

}
