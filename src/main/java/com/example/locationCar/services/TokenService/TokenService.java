package com.example.locationCar.services.TokenService;

import com.example.locationCar.dtos.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class TokenService {


        public TokenService() {

    }


    public TokenDto gerarToken(String email) {

        //String secretKey = System.getenv("SECRET_KEY");
        //Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime()+3600000);

        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(dataExpiracao)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        TokenDto tokenDto = new TokenDto(token, dataExpiracao);
        return tokenDto;

    }

}
