package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {

    private final ListVehicleService listVehicleService;

    public VehicleController(ListVehicleService listVehicleService) {
        this.listVehicleService = listVehicleService;
    }

    @GetMapping("/list")
    public BaseDto listVehicles(@RequestParam(required = false) Integer page) {
        return listVehicleService.listVehicles(page);
    }
}
