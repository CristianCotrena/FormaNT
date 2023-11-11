package com.example.locationCar.controllers;

import com.example.locationCar.dtos.LoginRequest;
import com.example.locationCar.dtos.TokenDto;
import com.example.locationCar.services.TokenService.LoginService;
import com.example.locationCar.services.core.EncryptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final EncryptService encryptService;

    public LoginController(LoginService loginService, EncryptService encryptService) {
        this.loginService = loginService;
        this.encryptService = encryptService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> efetuarLogin (@RequestBody LoginRequest loginRequest){

//       String minhaSenha = encryptService.encrypt("adrielly123");
//        System.out.println(minhaSenha);

        TokenDto tokenDto = loginService.efetuarLogin(loginRequest);

       return ResponseEntity.status(HttpStatus.OK).body(tokenDto);

    }
}

