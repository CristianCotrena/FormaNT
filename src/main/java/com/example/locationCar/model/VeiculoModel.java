package com.example.locationCar.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_VEICULOS")
public class VeiculoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private int numPortas; // 2 ou 4
    private String cor;
    private String combustivel;
    private BigDecimal valorDiaria;
    private BigDecimal quilometragem;
    private int status; // exclusão lógica
    private BigDecimal classificacao;
}
