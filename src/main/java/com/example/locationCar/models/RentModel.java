package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_ALUGUEL")
public class AluguelModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAluguel;
    private Date dataContratacao;
    private Date dataDevolucao;
    private Integer possuiSeguro;
    private String statusVeiculo;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientModel idClient;

    @ManyToOne
    @JoinColumn(name = "funcionarioId")
    private FuncionarioModel idFuncionario;

//    @ManyToOne
//    @JoinColumn(name = "veiculoId")
//    private VeiculoModel idVeiculo;

    public UUID getIAluguel() {
        return idAluguel;
    }
    public void setIdAluguel(UUID idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Date getDataContratacao() {
        return this.dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Date getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getPossuiSeguro() {
        return this.possuiSeguro;
    }

    public void setPossuiSeguro(Integer possuiSeguro) {
        this.possuiSeguro = possuiSeguro;
    }

    public String getStatusVeiculo() {
        return this.statusVeiculo;
    }

    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

}