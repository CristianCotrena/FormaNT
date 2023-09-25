package com.example.locationCar.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_VEHICLE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVehicle;

    private String license;
    private String brand;
    private String model;
    private Integer doorNumber; // 2 ou 4
    private String color;
    private String fuel;
    private BigDecimal dailyValue;
    private BigDecimal mileage;
    private Integer status;
    private BigDecimal rating;
}
