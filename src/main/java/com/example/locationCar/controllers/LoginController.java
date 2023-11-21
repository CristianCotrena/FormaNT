package com.example.locationCar.controllers;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.LoginInputDto;
import com.example.locationCar.services.loginService.CreateLoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login", description = "Operations about login")
public class LoginController {

    private CreateLoginService createLoginService;

    public LoginController(CreateLoginService createLoginService) {
        this.createLoginService = createLoginService;
    }

    @PostMapping
    public ResponseEntity<BaseDto> createLogin(@RequestBody LoginInputDto loginInputDto) {
        BaseDto baseDto = createLoginService.inserir(loginInputDto);
        return ResponseEntity.status(baseDto.getResult().getStatusCode()).body(baseDto);
    }
}
