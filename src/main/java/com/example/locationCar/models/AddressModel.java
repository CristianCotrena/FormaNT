package com.example.locationCar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAddress;
    private String publicPlace;
    private String road;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String cep;
    private Integer status;

    @OneToOne
    private ClientModel idClient;

    @OneToOne
    private EmployeeModel idEmployee;

    public ClientModel getClient() {
        return idClient;
    }

    public void setClient(ClientModel idClient) {
        this.idClient = idClient;
    }

    public EmployeeModel getEmployee() {
        return idEmployee;
    }

    public void setEmployee(EmployeeModel idEmployee) {
        this.idEmployee = idEmployee;
    }

    public UUID getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(UUID idAddress) {
        this.idAddress = idAddress;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getRoad() {
        return road;
    }
    public void setRoad(String road) {
        this.road = road;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
