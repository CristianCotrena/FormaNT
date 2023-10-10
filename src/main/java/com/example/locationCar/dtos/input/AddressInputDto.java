package com.example.locationCar.dtos.input;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddressInputDto {
    @Schema(example = "cd065886-1640-4190-9c5d-a5432cf93767")
    private Long idClient;
    @Schema(example = "cd065886-1640-4190-9c5d-a5432cf93767")
    private Long idEmployee;
    @Schema(example = "74840300")
    private String cep;
    @Schema(example = "2")
    private Integer number;
    @Schema(example = "Casa de Esquina, perto do mercado Moreira;")
    private String complement;

    public AddressInputDto() {
    }

    public AddressInputDto(Long idClient, Long idEmployee, String cep, Integer number, String complement) {
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.cep = cep;
        this.number = number;
        this.complement = complement;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
