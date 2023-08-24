package com.example.locationCar.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENTS")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    private UUID idClient;

    @Schema(example = "Carlitos Tevez")
    private String name;

    @Schema(example = "43576334222")
    private String cpfCnpj;

    @Schema(example = "874658935467")
    private String cnh;

    @Schema(example = "23")
    private Integer age;

    @Schema(example = "11999999999")
    private String telephone;

    @Schema(example = "11999999998")
    private String emergencyContact;

    @Schema(example = "carlitosboca@gmail.com")
    private String email;

    @Schema(hidden = true)
    private Integer status = 1;

    public ClientModel() {
    }

    public ClientModel(UUID idClient, String name, String cpfCnpj, String cnh, int age, String telephone, String emergencyContact, String email, int status) {
        this.idClient = idClient;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.cnh = cnh;
        this.age = age;
        this.telephone = telephone;
        this.emergencyContact = emergencyContact;
        this.email = email;
        this.status = status;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
