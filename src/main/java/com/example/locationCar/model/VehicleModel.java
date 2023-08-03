package com.example.locationCar.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_VEHICLE")
public class VehicleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVehicle;

    private String license;

    private String brand;

    private String model;


    private int doorNumber; // 2 ou 4

    private String color;
    private String fuel;

    private BigDecimal dailyValue;

    private BigDecimal mileage;

    private int status;

    private BigDecimal rating;

}
