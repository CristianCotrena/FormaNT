package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.services.rentService.UpdateRentService;
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

  @PutMapping("/{id}")
  public ResponseEntity<BaseDto> updateRent(
      @PathVariable String id, @RequestBody RentUpdateDto rentUpdateDto) {
    BaseDto baseDto = updateRentService.updateRent(id, rentUpdateDto);

    return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
  }
}
