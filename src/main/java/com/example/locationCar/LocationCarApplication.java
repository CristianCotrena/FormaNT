package com.example.locationCar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info =
        @Info(
            title = "FormaNT - Car Rental",
            version = "1.0.0",
            description = "Projeto para aluguel de carros"))
@SpringBootApplication
public class LocationCarApplication {
  public static void main(String[] args) {
    SpringApplication.run(LocationCarApplication.class, args);
  }
}
