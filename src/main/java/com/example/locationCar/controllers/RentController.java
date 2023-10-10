package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.RentInputDto;
import com.example.locationCar.services.rentService.CreateRentService;
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

@RestController
@RequestMapping("/v1/rent")
@Tag(name = "Rent", description = "Operations about rent")
public class RentController {

    private CreateRentService createRentService;
    public RentController(CreateRentService createRentService) {
        this.createRentService = createRentService;
    }
    @Operation(summary = "Create Rent", description = "Add a rent to the database")
    @ApiResponse(responseCode = "201", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Aluguel criado com sucesso."))
    })
    @ApiResponse(responseCode = "404", description = "Rent not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Bad Request")),
    })
    @PostMapping
    public ResponseEntity<BaseDto> createRent(@RequestBody RentInputDto rentInputDto) {
        BaseDto baseDto = createRentService.inserir(rentInputDto);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }
}
