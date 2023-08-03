package com.example.locationCar.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENTS")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String name;
    private String cpfCnpj;
    private String cnh;
    private int age;
    private String telephone;
    private String emergencyContact;
    private String email;
    private int status = 1;
    private String password;


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}
