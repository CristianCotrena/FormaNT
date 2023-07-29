package com.example.locationCar.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name= "TB_FUNCIONARIO")
public class FuncionarioModel extends RepresentationModel<FuncionarioModel> implements Serializable {
        private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFuncionario;
    private String name;
    private String cargo;
    private String cpfCnpj;
    private String registro;
    private String telefone;
    private String tipoContrato;
    private String role;
    private String email;
    private int status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioModel that = (FuncionarioModel) o;
        return status == that.status && Objects.equals(idFuncionario, that.idFuncionario) && Objects.equals(name, that.name) && Objects.equals(cargo, that.cargo) && Objects.equals(cpfCnpj, that.cpfCnpj) && Objects.equals(registro, that.registro) && Objects.equals(telefone, that.telefone) && Objects.equals(tipoContrato, that.tipoContrato) && Objects.equals(role, that.role) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFuncionario, name, cargo, cpfCnpj, registro, telefone, tipoContrato, role, email, status);
    }

    @Override
    public String toString() {
        return "FuncionarioModel{" +
                "idFuncionario=" + idFuncionario +
                ", name='" + name + '\'' +
                ", cargo='" + cargo + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", registro='" + registro + '\'' +
                ", telefone='" + telefone + '\'' +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
