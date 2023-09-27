package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.services.RentService.ListRentByIdService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/rent")
public class RentController {

    private final ListRentByIdService listRentByIdService;

    public RentController(ListRentByIdService listRentByIdService) {
        this.listRentByIdService = listRentByIdService;
    }

    @GetMapping("/list")
    public ResponseEntity<BaseDto> listRentById(
            @RequestParam(required = false) UUID clientId,
            @RequestParam(required = false) UUID EmployeeId,
            Pageable pageable) {
        BaseDto baseDto = listRentByIdService.listRentById(clientId,EmployeeId,pageable);

        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);

    }

}
