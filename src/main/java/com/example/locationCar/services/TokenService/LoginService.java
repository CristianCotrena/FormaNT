package com.example.locationCar.services.TokenService;

import com.example.locationCar.dtos.LoginRequest;
import com.example.locationCar.dtos.TokenDto;
import com.example.locationCar.models.LoginModel;
import com.example.locationCar.repositories.LoginRepository;
import com.example.locationCar.services.core.EncryptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final EncryptService encryptService;
    private final LoginRepository loginRepository;
    private final TokenService tokenService;

    public TokenDto efetuarLogin(LoginRequest loginRequest) {

        Boolean isValid = encryptService.compare(loginRequest.getSenha(), loginRequest.getEmail());

        if (isValid) {
            return tokenService.gerarToken(loginRequest.getEmail());
        } else {
            throw new IllegalArgumentException("Usuario ou senha invalidos");
        }
    }
}


