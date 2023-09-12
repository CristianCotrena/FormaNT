package com.example.locationCar.dtos.input;

public class AddressInputDto {
    private String idClient;
    private String idEmployee;
    private String cep;
    private Integer number;
    private String complement;

    public AddressInputDto() {
    }

    public AddressInputDto(String idClient, String idEmployee, String cep, Integer number, String complement) {
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.cep = cep;
        this.number = number;
        this.complement = complement;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
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
